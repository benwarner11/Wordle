package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Game extends ApplicationAdapter {

	ShapeRenderer sr;
	private SpriteBatch batch;
	OrthographicCamera cam;
	BitmapFont fontTiles;
	BitmapFont fontKeys;

	File words;
	Scanner scanner;

	public static ArrayList<Tile> tiles = new ArrayList<>();
	public static ArrayList<Key> keyboard = new ArrayList<>();

	String keys = "QWERTYUIOPASDFGHJKLZXCVBNM", word;
	int width, height, line = 0, pos = 0;
	static char keyPress;

	@Override
	public void create() {

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		FreeTypeFontGenerator fg = new FreeTypeFontGenerator(Gdx.files.internal("Franklin Gothic Regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter fp = new FreeTypeFontGenerator.FreeTypeFontParameter();

		fp.size = 20;
		fp.color = Color.BLACK;
		fontTiles = fg.generateFont(fp);

		fp.size = 15;
		fp.color = Color.BLACK;
		fontKeys = fg.generateFont(fp);

		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 5; i++) {
				tiles.add(new Tile(i, j));
			}
		}

		for (int i = 0; i < 10; i++) {
			keyboard.add(new Key(width / 66F + i * width / 66F + i * width / 12F, height / 4.4F, keys.charAt(i)));
		}
		for (int i = 0; i < 9; i++) {
			keyboard.add(new Key((width - 9 * width / 12F - 8 * width / 66F) / 2F + i * width / 12F + i * width / 66F, height / 4.4F - height / 16F - width / 66F, keys.charAt(i + 10)));
		}
		for (int i = 0; i < 7; i++) {
			keyboard.add(new Key((width - 7 * width / 12F - 6 * width / 66F) / 2F + i * width / 12F + i * width / 66F, height / 4.4F - 2 * (height / 16F + width / 66F), keys.charAt(i + 19)));
		}
		keyboard.add(new SpecialKey(keyboard.get(25).x + + width/12F + width/66F, keyboard.get(25).y, '~', keyboard.get(19).x - keyboard.get(0).x - width/66F));
		keyboard.add(new SpecialKey(keyboard.get(0).x, keyboard.get(19).y, '#', keyboard.get(19).x - keyboard.get(0).x - width/66F));

		try {
			words = new File("assets/words.txt");
			scanner = new Scanner(words);
			int length = 0;
			//get random word
			while (scanner.hasNextLine()){
				length += 1;
				scanner.nextLine();
			}
			System.out.println(length);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 1, 1, 1);
		keyPress = ' ';
		for (Tile i : tiles) {
			i.render(sr);
			renderTileLetters(i.letter, i.xpos, i.ypos);
		}
		for (Key i : keyboard) {
			i.render(sr);
			renderKeyLetters(i.letter, i.x, i.y);
			i.click();
		}

		if (keyPress == ' '){
			pollKeyboard();
		} else{
			modifyTiles(keyPress);
		}
	}

	@Override
	public void dispose() {
		sr.dispose();
	}

	public void pollKeyboard() {
		int keycode = Gdx.input.isKeyJustPressed(Input.Keys.A) ? Input.Keys.A :
				Gdx.input.isKeyJustPressed(Input.Keys.B) ? Input.Keys.B :
						Gdx.input.isKeyJustPressed(Input.Keys.C) ? Input.Keys.C :
								Gdx.input.isKeyJustPressed(Input.Keys.D) ? Input.Keys.D :
										Gdx.input.isKeyJustPressed(Input.Keys.E) ? Input.Keys.E :
												Gdx.input.isKeyJustPressed(Input.Keys.F) ? Input.Keys.F :
														Gdx.input.isKeyJustPressed(Input.Keys.G) ? Input.Keys.G :
																Gdx.input.isKeyJustPressed(Input.Keys.H) ? Input.Keys.H :
																		Gdx.input.isKeyJustPressed(Input.Keys.I) ? Input.Keys.I :
																				Gdx.input.isKeyJustPressed(Input.Keys.J) ? Input.Keys.J :
																						Gdx.input.isKeyJustPressed(Input.Keys.K) ? Input.Keys.K :
																								Gdx.input.isKeyJustPressed(Input.Keys.L) ? Input.Keys.L :
																										Gdx.input.isKeyJustPressed(Input.Keys.M) ? Input.Keys.M :
																												Gdx.input.isKeyJustPressed(Input.Keys.N) ? Input.Keys.N :
																														Gdx.input.isKeyJustPressed(Input.Keys.O) ? Input.Keys.O :
																																Gdx.input.isKeyJustPressed(Input.Keys.P) ? Input.Keys.P :
																																		Gdx.input.isKeyJustPressed(Input.Keys.Q) ? Input.Keys.Q :
																																				Gdx.input.isKeyJustPressed(Input.Keys.R) ? Input.Keys.R :
																																						Gdx.input.isKeyJustPressed(Input.Keys.S) ? Input.Keys.S :
																																								Gdx.input.isKeyJustPressed(Input.Keys.T) ? Input.Keys.T :
																																										Gdx.input.isKeyJustPressed(Input.Keys.U) ? Input.Keys.U :
																																												Gdx.input.isKeyJustPressed(Input.Keys.V) ? Input.Keys.V :
																																														Gdx.input.isKeyJustPressed(Input.Keys.W) ? Input.Keys.W :
																																																Gdx.input.isKeyJustPressed(Input.Keys.X) ? Input.Keys.X :
																																																		Gdx.input.isKeyJustPressed(Input.Keys.Y) ? Input.Keys.Y :
																																																				Gdx.input.isKeyJustPressed(Input.Keys.Z) ? Input.Keys.Z :
																																																						Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ? Input.Keys.ENTER :
																																																								Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) ? Input.Keys.BACKSPACE :
																																																										-1;
		switch (keycode) {
			case (Input.Keys.A):
				keyPress = 'A';
				break;
			case (Input.Keys.B):
				keyPress = 'B';
				break;
			case (Input.Keys.C):
				keyPress = 'C';
				break;
			case (Input.Keys.D):
				keyPress = 'D';
				break;
			case (Input.Keys.E):
				keyPress = 'E';
				break;
			case (Input.Keys.F):
				keyPress = 'F';
				break;
			case (Input.Keys.G):
				keyPress = 'G';
				break;
			case (Input.Keys.H):
				keyPress = 'H';
				break;
			case (Input.Keys.I):
				keyPress = 'I';
				break;
			case (Input.Keys.J):
				keyPress = 'J';
				break;
			case (Input.Keys.K):
				keyPress = 'K';
				break;
			case (Input.Keys.L):
				keyPress = 'L';
				break;
			case (Input.Keys.M):
				keyPress = 'M';
				break;
			case (Input.Keys.N):
				keyPress = 'N';
				break;
			case (Input.Keys.O):
				keyPress = 'O';
				break;
			case (Input.Keys.P):
				keyPress = 'P';
				break;
			case (Input.Keys.Q):
				keyPress = 'Q';
				break;
			case (Input.Keys.R):
				keyPress = 'R';
				break;
			case (Input.Keys.S):
				keyPress = 'S';
				break;
			case (Input.Keys.T):
				keyPress = 'T';
				break;
			case (Input.Keys.U):
				keyPress = 'U';
				break;
			case (Input.Keys.V):
				keyPress = 'V';
				break;
			case (Input.Keys.W):
				keyPress = 'W';
				break;
			case (Input.Keys.X):
				keyPress = 'X';
				break;
			case (Input.Keys.Y):
				keyPress = 'Y';
				break;
			case (Input.Keys.Z):
				keyPress = 'Z';
				break;
			case (Input.Keys.ENTER):
				keyPress = '#';
				break;
			case (Input.Keys.BACKSPACE):
				keyPress = '~';
				break;
			default:
				return;
		}
		modifyTiles(keyPress);
	}

	public void modifyTiles(char keyPress) throws IndexOutOfBoundsException {
		if (keyPress != '#' && keyPress != '~'){
			if (pos < 5) {
				tiles.get(5 * line + pos).letter = keyPress;
				pos++;
			}
		}
		if (keyPress == '~'){
			if (pos < 6 && pos > 0) {
				pos--;
				tiles.get(5 * line + pos).letter = ' ';
			}
		}
		if (keyPress == '#'){
			if ((5 * line + pos) % 5 == 0 && pos != 0){
				if (check()){
					pos = 0;
					line ++;
				}
			}
		}
	}

	public boolean check(){
		String guess = "" + tiles.get(5 * line).letter + tiles.get(5 * line + 1).letter + tiles.get(5 * line + 2).letter + tiles.get(5 * line + 3).letter + tiles.get(5 * line + 4).letter;
		if (guess.equals(word)){
			//do something
		} else {
			checkLetters(guess);
		}
		return true;
	}

	public void search(){

	}

	void checkLetters(String guess){

	}

	void renderTileLetters(char letter, float xpos, float ypos){
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		fontTiles.draw(batch, Character.toString(letter), xpos + width/12F - fontTiles.getXHeight()/2, ypos + width/12F);
		batch.end();
	}

	void renderKeyLetters(char letter, float x, float y){
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		fontKeys.draw(batch, Character.toString(letter), x + width/24F - fontKeys.getXHeight()/2, y + height/32F);
		batch.end();
	}
}
