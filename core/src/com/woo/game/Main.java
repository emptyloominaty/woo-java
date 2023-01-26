package com.woo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.woo.game.objects.Keybinds;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.Settings;
import com.woo.game.ui.UiMain;
import space.earlygrey.shapedrawer.ShapeDrawer;

import com.woo.game.objects.gameobjects.*;
import com.woo.game.objects.gameobjects.creatures.Player;

import java.util.HashMap;
import java.util.Map;


public class Main extends ApplicationAdapter {
	public static SpriteBatch batch;
	//Texture img;
	ParticleEffect test;

	float fireX;
	float fireY;

	final int WORLD_WIDTH = 4096;
	final int WORLD_HEIGHT = 4096;
	OrthographicCamera cam;
	public static Sprite mapSprite;

	float rotationSpeed = 0.5f;

	public static Player player;

	BitmapFont font;
	ShapeDrawer shapeDrawer;

	public static Vector2 mouseInWorld2D = new Vector2();
	public static Vector3 mouseInWorld3D = new Vector3();

	public static double[] debugPerf = new double[64];
	public static String[] debug = new String[64];

	float delta;
	float effectiveViewportWidth;
	float effectiveViewportHeight;
	UiMain uiMain = new UiMain();

	@Override
	public void create () {
		GlobalVars.init();
		GOControl.reset();

		//Settings.init();
		Keybinds.init();

		ParticleSystem.init();

		player = new Player("Player","",false,false,250,250,"",0,"FireMage");
		GOControl.addCreature(player);

		//--Test
		Creature testCreature = new Creature("test","test",false,false,50,40,"",1,"test");
		GOControl.addCreature(testCreature);
		Creature testCreature2 = new Creature("test2","test",false,false,150,40,"",2,"test");
		GOControl.addCreature(testCreature2);
		Spell testSpell = new Spell("test3","test",false,false,500,40,"");
		GOControl.addSpell(testSpell);
		Item testItem = new Item("test4","test",false,false,450,40,"");
		GOControl.addItem(testItem);
		WorldObject testWorldObject = new WorldObject("test5","test",false,false,250,40,"");
		GOControl.addWorldObject(testWorldObject);
		Creature testCreature3 = new Creature("test6","test",false,false,80,250,"",1,"test");
		GOControl.addCreature(testCreature3);

		GOControl.removeGameObject(1);
		/*for (int i = 0; i<GOControl.gameObjects.size(); i++) {
			System.out.println(i+": ");
			GOControl.gameObjects.get(i).test();
		}*/
		Player testxD = (Player) GOControl.creatures.get(0);//.testPlayer();
		testxD.testPlayer();
		GOControl.creatures.get(0).test();
		//--End Test


		//---------------------------------------------------------------------------
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		font = new BitmapFont();

		test = new ParticleEffect();
		test.load(Gdx.files.internal("particles/fire.particle"),Gdx.files.internal("textures"));
		test.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);

		//TODO:  low(100) - medium(200) - high(400) - ultra(500) ????
		//test.getEmitters().first().getEmission().


		mapSprite = new Sprite(new Texture(Gdx.files.internal("sc_map.png")));
		mapSprite.setPosition(0, 0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(1200, 1200 * (h / w));
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();

		shapeDrawer = new ShapeDrawer(batch, mapSprite);

		ParticleEmitter emitter = test.getEmitters().first();

		/*Array<Sprite> fireSprite = new Array<Sprite>();
		fireSprite.add(new Sprite(new Texture(Gdx.files.internal( "textures/fire.png"))));
		emitter.setSprites(fireSprite);*/

		int fireAngle = 0;
		emitter.getAngle().setHigh(fireAngle-45,fireAngle+45);
		emitter.getAngle().setLow(fireAngle);
		test.start();

		fireX = Gdx.graphics.getWidth()/2f;
		fireY = Gdx.graphics.getHeight()/2f;

		uiMain.create();
		GameInput.setInputProcessor(uiMain.stage);

		debugPerf[63] = 0;
		debugPerf[0] = 0;
	}

	public void updateGame() {
		player.main2();
		uiMain.setPlayerHealthBar(player.health, player.healthMax);

		for (int i = GOControl.creatures.size()-1; i>-1; i--) {
			if (!GOControl.creatures.get(i).destroyed) {
				//TODO:
				//GOControl.creatures.get(i).main();
			}
		}
	}

	@Override
	public void render () {
		debugPerf[0] = System.currentTimeMillis();
		if (debugPerf[0]-debugPerf[63]>80) {
			System.out.println("Time Between Frames:"+(debugPerf[0]-debugPerf[63]));
		}
		delta = Gdx.graphics.getDeltaTime();
		if (delta>0.07f) {
			delta = 0.07f;
		}
		GlobalVars.delta = delta;
		GlobalVars.fps = 1/delta;

		//input
		GameInput.handleInput();
		handleInput();

		updateGame();

		debugPerf[30] = System.currentTimeMillis();
		//Render Start
		ScreenUtils.clear(0, 0, 0, 1);

		//camera
		cam.zoom = MathUtils.clamp(GlobalVars.camZoom, 0.1f, 4000/cam.viewportWidth);
		effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		effectiveViewportHeight = cam.viewportHeight * cam.zoom;
		cam.position.x = (float) MathUtils.clamp(player.x, effectiveViewportWidth / 2f, 1200 - effectiveViewportWidth / 2f);
		cam.position.y = (float) MathUtils.clamp(player.y, effectiveViewportHeight / 2f, 1200 - effectiveViewportHeight / 2f);
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		//Mouse position in Game
		mouseInWorld3D.x = Gdx.input.getX();
		mouseInWorld3D.y = Gdx.input.getY();
		mouseInWorld3D.z = 0;
		cam.unproject(mouseInWorld3D);
		mouseInWorld2D.x = mouseInWorld3D.x;
		mouseInWorld2D.y = mouseInWorld3D.y;

		//fireX-=delta*300;
		test.setPosition(fireX,fireY);
		test.update(delta);

		batch.begin();
		//batch.draw(img, 0, 0);
		mapSprite.draw(batch);

		font.draw(batch, "x:"+Math.round(player.x)+" y:"+Math.round(player.y)+" dir:"+Math.round(player.direction), player.x, player.y+30);
		font.draw(batch, "fps:"+GlobalVars.fps, player.x, player.y+50);
		font.draw(batch, "zoom:"+GlobalVars.camZoom, player.x, player.y+70);
		font.draw(batch, "P:"+ParticleSystem.particleList.size(), player.x, player.y+90);

		//objects draw
		for (int i = 0; i<GOControl.gameObjects.size(); i++) {
			GOControl.gameObjects.get(i).draw(shapeDrawer);
		}

		//spells/particles draw
		for (int i = 0; i< ParticleSystem.particleList.size(); i++) {
			if (ParticleSystem.particleList.get(i)!=null) {
				ParticleSystem.particleList.get(i).update(delta);
				ParticleSystem.particleList.get(i).draw(batch);
				if (ParticleSystem.particleList.get(i).isComplete()) {
					ParticleSystem.remove(i);
				}
			}
		}

		//fire test
		test.draw(batch);

		batch.end();

		uiMain.render();

		debugPerf[63] = System.currentTimeMillis();
		if (debugPerf[63]-debugPerf[0]>10) {
			System.out.println("Total:"+(debugPerf[63]-debugPerf[0])+", Main:"+(debugPerf[30]-debugPerf[0])+", Draw:"+(debugPerf[63]-debugPerf[30]));
		}
		//TODO:UI FPS + (Total + Main + Draw) Time
	}

	public void resize(int width, int height) {
		System.out.println(width+" | "+height);
		uiMain.resize(width, height);
	}

	private void handleInput() {
		/*if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.zoom += 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			cam.zoom -= 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cam.translate(-3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.translate(3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.translate(0, -3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.translate(0, 3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			cam.rotate(-rotationSpeed, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			cam.rotate(rotationSpeed, 0, 0, 1);
		}

		cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 4000/cam.viewportWidth);

		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

		cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 4000 - effectiveViewportWidth / 2f);
		cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 4000 - effectiveViewportHeight / 2f);*/
	}

	@Override
	public void dispose () {
		batch.dispose();
		mapSprite.getTexture().dispose();
		uiMain.dispose();
		//TODO:
		//img.dispose();
	}
}
