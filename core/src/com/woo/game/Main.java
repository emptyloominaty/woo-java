package com.woo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import space.earlygrey.shapedrawer.ShapeDrawer;

import com.woo.game.objects.gameobjects.*;
import com.woo.game.objects.gameobjects.creatures.Player;


public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	ParticleEffect test;

	float fireX;
	float fireY;

	final int WORLD_WIDTH = 4096;
	final int WORLD_HEIGHT = 4096;
	OrthographicCamera cam;
	Sprite mapSprite;

	float rotationSpeed = 0.5f;

	public static Player player;

	BitmapFont font;
	ShapeDrawer shapeDrawer;
	
	@Override
	public void create () {

		GlobalVars.init();
		GOControl.reset();

		player = new Player("Player","",false,false,250,250,"",0,"FireMage");
		GOControl.addCreature(player);

		//--Test
		Creature testCreature = new Creature("test","test",false,false,50,40,"",1,"test");
		GOControl.addCreature(testCreature);
		Creature testCreature2 = new Creature("test2","test",false,false,50,40,"",2,"test");
		GOControl.addCreature(testCreature2);
		Spell testSpell = new Spell("test3","test",false,false,50,40,"");
		GOControl.addSpell(testSpell);
		Item testItem = new Item("test4","test",false,false,50,40,"");
		GOControl.addItem(testItem);
		WorldObject testWorldObject = new WorldObject("test5","test",false,false,50,40,"");
		GOControl.addWorldObject(testWorldObject);
		Creature testCreature3 = new Creature("test6","test",false,false,50,40,"",1,"test");
		GOControl.addCreature(testCreature3);

		GOControl.removeGameObject(1);
		for (int i = 0; i<GOControl.gameObjects.size(); i++) {
			System.out.println(i+": ");
			GOControl.gameObjects.get(i).test();

		}
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


		mapSprite = new Sprite(new Texture(Gdx.files.internal("sc_map.png")));
		mapSprite.setPosition(0, 0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(800, 800 * (h / w));
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
	}

	@Override
	public void render () {
		float delta = Gdx.graphics.getDeltaTime();
		GlobalVars.delta = delta;
		GlobalVars.fps = 1/delta;

		//input
		GameInput.handleInput();
		handleInput();

		//TODO: Main?()


		ScreenUtils.clear(0, 0, 0, 1);

		//camera
		cam.zoom = MathUtils.clamp(GlobalVars.camZoom, 0.1f, 4000/cam.viewportWidth);
		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
		cam.position.x = (float) MathUtils.clamp(player.x, effectiveViewportWidth / 2f, 4000 - effectiveViewportWidth / 2f);
		cam.position.y = (float) MathUtils.clamp(player.y, effectiveViewportHeight / 2f, 4000 - effectiveViewportHeight / 2f);


		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		//fireX-=delta*300;
		test.setPosition(fireX,fireY);
		test.update(delta);

		batch.begin();
		//batch.draw(img, 0, 0);
		mapSprite.draw(batch);
		test.draw(batch);
		font.draw(batch, "x:"+Math.round(player.x)+" y:"+Math.round(player.y)+" dir:"+Math.round(player.direction)+"TEST:"+cam.zoom, player.x, player.y+30);
		font.draw(batch, "fps:"+GlobalVars.fps, player.x, player.y+50);

		//player
		shapeDrawer.setColor(Color.BLUE);
		shapeDrawer.filledCircle(player.x, player.y, 14);
		shapeDrawer.setColor(Color.WHITE);
		double playerDirectionRadian = (player.direction-180) / 180 * Math.PI;
		shapeDrawer.line(player.x, player.y, (float) (player.x+(10*Math.sin(playerDirectionRadian))), (float) (player.y+(10*Math.cos(playerDirectionRadian))));

		batch.end();

		if (test.isComplete()) {
			test.scaleEffect(0.5f);
			test.reset();
		}
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
		//TODO:
		//img.dispose();
	}
}
