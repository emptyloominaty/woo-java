package com.woo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.woo.game.objects.Keybinds;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.SpellQueue;
import com.woo.game.ui.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import com.woo.game.objects.gameobjects.*;
import com.woo.game.objects.gameobjects.creatures.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class Main extends ApplicationAdapter {
	public static String areaName = "Test"; // TODO:

	public static SpriteBatch batch;
	//Texture img;

	final int WORLD_WIDTH = 4096;
	final int WORLD_HEIGHT = 4096;
	public static OrthographicCamera cam;
	public static Sprite mapSprite;


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
	public static UiMain uiMain = new UiMain();

	public static ActionBar[] actionBars = new ActionBar[2];
	public static Map<String,Action> actions = new HashMap<String,Action>();

	Rectangle viewportRect = new Rectangle();

	public static BuffBar buffBar;

	@Override
	public void create () {
		Maps.init();
		actionBars[0] = new ActionBar(15,new String[15],0);
		actionBars[1] = new ActionBar(15,new String[15],1);

		//test TODO:function + check class
		actions.put("Fire Blast",new Action("Fire Blast",0,0));
		actions.put("Fire Ball",new Action("Fire Ball",0,1));
		actions.put("Wildfire",new Action("Wildfire",0,2));
		actions.put("Blink",new Action("Blink",0,3));
		actions.put("Healing",new Action("Healing",0,4));
		actions.put("Regenerate",new Action("Regenerate",0,5));

		GlobalVars.init();
		GOControl.reset();

		//Settings.init();
		Keybinds.init();

		ParticleSystem.init();

		player = new Player("Player","",false,false,250,250,"",0,"Fire Mage",0);
		GOControl.addCreature(player);

		//--Test
		Creature testCreature = new Creature("test","test",false,false,50,50,"",3,"Enemy1",0);
		GOControl.addCreature(testCreature);
		Creature testCreature2 = new Creature("test2","test",false,false,800,250,"",3,"Enemy2",0);
		GOControl.addCreature(testCreature2);
		ItemW testItem = new ItemW("test4","test",false,false,450,40,"",0);
		GOControl.addItem(testItem);
		WorldObject testWorldObject = new WorldObject("test5","test",false,false,250,40,"",0);
		GOControl.addWorldObject(testWorldObject);
		Creature testCreature3 = new Creature("test6","test",false,false,80,250,"",3,"Enemy3",0);
		GOControl.addCreature(testCreature3);
		Creature testCreature4 = new Creature("test7","test",false,false,450,350,"",3,"Enemy1",0);
		GOControl.addCreature(testCreature4);

		GOControl.removeGameObject(1);
		/*for (int i = 0; i<GOControl.gameObjects.size(); i++) {
			System.out.println(i+": ");
			GOControl.gameObjects.get(i).test();
		}*/
		Player testxD = (Player) GOControl.creatures.get(0);//.testPlayer();
		testxD.testPlayer();
		GOControl.creatures.get(0).test();
		/*for (int i = 0; i<5000 ;i++) {
			WorldObject worldObj = new WorldObject("testForLoop"+i,"loop",false,false,700,700,"",3);
			worldObj.canStopSpells = true;
			GOControl.addWorldObject(worldObj);
		}*/
		for (int x = 0; x<1000 ;x++) {
			GOControl.addCreature(new Creature("testx"+x,"test",false,false,(float)Math.random()*10000,(float)Math.random()*10000,"",3,"test",0));
			/*GOControl.creatures.get(x+4).healthMax = 9000;
			GOControl.creatures.get(x+4).health = 9000;*/
		}

		//--End Test


		//---------------------------------------------------------------------------
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		font = new BitmapFont();


		//TODO:  low(100) - medium(200) - high(400) - ultra(500) ????
		//test.getEmitters().first().getEmission().


		mapSprite = new Sprite(new Texture(Gdx.files.internal("sc_map.png")));
		mapSprite.setPosition(0, 0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(w, w * (h / w)); //1200 , 1200
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();

		shapeDrawer = new ShapeDrawer(batch, mapSprite);

		//ParticleEmitter emitter = test.getEmitters().first();
		/*Array<Sprite> fireSprite = new Array<Sprite>();
		fireSprite.add(new Sprite(new Texture(Gdx.files.internal( "textures/fire.png"))));
		emitter.setSprites(fireSprite);*/
		/*int fireAngle = 0;
		emitter.getAngle().setHigh(fireAngle-45,fireAngle+45);
		emitter.getAngle().setLow(fireAngle);
		test.start();*/

		uiMain.create();
		GameInput.setInputProcessor(uiMain.stage);
		uiMain.createActionBars(true,15);
		uiMain.createActionBars(false,15);

		//TEST
		for (int i = GOControl.creatures.size()-1; i>-1; i--) {
			uiMain.addCreatureBar(GOControl.creatures.get(i));
		}

		player.inventory.init();
		player.inventory.createUi();

		//TEST
		player.stats.put("stamina",80.0);
		player.statsB.put("stamina",80.0);

		buffBar = new BuffBar();

		debugPerf[63] = 0;
		debugPerf[0] = 0;
	}



	public void updateEverySec() {
		uiMain.areaNameLabel.setText(areaName);
	}

	float timer1 = 0;
	float timer2 = 1;

	public void updateGame() {
		if (timer2>timer1) {
			timer1+=GlobalVars.delta;
		} else {
			timer1 = 0;
			updateEverySec();
		}
		if (GlobalVars.characterStats) {
			uiMain.updateCharacterStats();
		}

		uiMain.updateFloatingTexts();

		uiMain.gcdTimerSet(15,0);
		uiMain.gcdTimerSet(15,1);

		player.main2();
		uiMain.setPlayerHealthBar(player.health, player.healthMax);
		uiMain.setPlayerManaBar(player.energy, player.energyMax);
		uiMain.setPlayerSecBar(player.secondaryResource, player.secondaryResourceMax);
		uiMain.setPlayerXpBar(player.xp-(GlobalFunctions.xpToNextLevel(player.level-1)),(GlobalFunctions.xpToNextLevel(player.level))-(GlobalFunctions.xpToNextLevel(player.level-1)));

		for (int i = GOControl.creatures.size()-1; i>-1; i--) {
			if (!GOControl.creatures.get(i).destroyed && !GOControl.creatures.get(i).isDead) {
				GOControl.creatures.get(i).main();
			} else {
				GOControl.creatures.get(i).x = -999;
				GOControl.creatures.get(i).y = -999;
			}
		}
		for (int i = GOControl.spells.size()-1; i>-1; i--) {
			if (!GOControl.spells.get(i).destroyed) {
				GOControl.spells.get(i).main();
			}

		}
		SpellQueue.main();
	}

	@Override
	public void render () {
		debugPerf[0] = System.currentTimeMillis();
		if (debugPerf[0]-debugPerf[63]>80) {
			System.out.println("Time Between Frames:"+(debugPerf[0]-debugPerf[63]));
		}
		delta = Gdx.graphics.getDeltaTime();
		if (delta>0.06f) {
			delta = 0.06f;
		}
		GlobalVars.delta = delta;
		GlobalVars.fps = 1/delta;

		for (int i = 0; i<actionBars.length; i++) {
			actionBars[i].main();
		}

		//input
		GameInput.handleInput();

		updateGame();

		debugPerf[30] = System.currentTimeMillis();
		//Render Start

		ScreenUtils.clear(0, 0, 0, 1);

		//camera
		cam.zoom = MathUtils.clamp(GlobalVars.camZoom, 0.1f, 8000/cam.viewportWidth);
		effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		effectiveViewportHeight = cam.viewportHeight * cam.zoom;

		cam.position.x = (float) MathUtils.clamp(player.x, effectiveViewportWidth / 2f , 4096 - effectiveViewportWidth / 2f);
		cam.position.y = (float) MathUtils.clamp(player.y, effectiveViewportHeight / 2f, 4096 - effectiveViewportHeight / 2f);
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		int viewPortOffset = 100;
		viewportRect.setBounds((int)(cam.position.x - (cam.viewportWidth*GlobalVars.camZoom+viewPortOffset) / 2),
				(int)(cam.position.y - (cam.viewportHeight*GlobalVars.camZoom+viewPortOffset) / 2),
				(int) (cam.viewportWidth*GlobalVars.camZoom+viewPortOffset),
				(int) (cam.viewportHeight*GlobalVars.camZoom+viewPortOffset));

		//Mouse position in Game
		mouseInWorld3D.x = Gdx.input.getX();
		mouseInWorld3D.y = Gdx.input.getY();
		mouseInWorld3D.z = 0;
		cam.unproject(mouseInWorld3D);
		mouseInWorld2D.x = mouseInWorld3D.x;
		mouseInWorld2D.y = mouseInWorld3D.y;

		batch.begin();
		//batch.draw(img, 0, 0);
		mapSprite.draw(batch);

		font.draw(batch, "zoom:"+GlobalVars.camZoom, player.x, player.y+30);
		font.draw(batch, "P:"+ParticleSystem.particleList.size(), player.x, player.y+50);

		//objects draw
		for (int i = 0; i<GOControl.gameObjects.size(); i++) {
			if (viewportRect.contains(GOControl.gameObjects.get(i).x, GOControl.gameObjects.get(i).y)) {
				GOControl.gameObjects.get(i).draw(shapeDrawer);
			}
		}
		for (int i = GOControl.creatures.size()-1; i>-1; i--) {
			uiMain.updateCreatureBar(GOControl.creatures.get(i));
		}

		//spells/particles draw
		ParticleSystem.run();
		for (int i = 0; i< ParticleSystem.particleList.size(); i++) {
			if (ParticleSystem.particleList.get(i)!=null) {
				ParticleSystem.particleList.get(i).update(delta);
				ParticleSystem.particleList.get(i).draw(batch);
				if (ParticleSystem.particleList.get(i).isComplete()) {
					ParticleSystem.remove(i);
				}
			}
		}

		batch.end();

		debugPerf[62] = System.currentTimeMillis();
		uiMain.render();
		//TODO: actions.get() .draw()

		debugPerf[63] = System.currentTimeMillis();
		if (debugPerf[63]-debugPerf[0]>17) {
			System.out.println("Total:"+(debugPerf[63]-debugPerf[0])+", Main:"+(debugPerf[30]-debugPerf[0])+", Draw:"+(debugPerf[63]-debugPerf[30]));
		}
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
