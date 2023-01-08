package com.woo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.woo.game.objects.gameobjects.GOControl;


public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	ParticleEffect test;

	float fireX;
	float fireY;

	final int WORLD_WIDTH = 5000;
	final int WORLD_HEIGHT = 5000;
	OrthographicCamera cam;
	Sprite mapSprite;

	float rotationSpeed = 0.5f;
	
	@Override
	public void create () {

		GOControl goControl = new GOControl();
		goControl.reset();



		//---------------------------------------------------------------------------
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");

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
		ScreenUtils.clear(0, 0, 0, 1);
		float delta = Gdx.graphics.getDeltaTime();

		handleInput();
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		//fireX-=delta*300;
		test.setPosition(fireX,fireY);
		test.update(delta);

		batch.begin();
		//batch.draw(img, 0, 0);
		mapSprite.draw(batch);
		test.draw(batch);
		batch.end();

		if (test.isComplete()) {
			test.scaleEffect(0.5f);
			test.reset();
		}
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
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
		cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 4000 - effectiveViewportHeight / 2f);
	}

	@Override
	public void dispose () {
		batch.dispose();
		mapSprite.getTexture().dispose();
		//img.dispose();
	}
}
