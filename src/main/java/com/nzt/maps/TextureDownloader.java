package com.nzt.maps;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.StreamUtils;

public class TextureDownloader extends Observable{
	
	
	public  void downloadImage(final String url) {
		new Thread(new Runnable() {
			/**
			 * Downloads the content of the specified url to the array. The array has to be
			 * big enough.
			 */
			private int download(byte[] out, String url) {
				InputStream in = null;
				try {
					HttpURLConnection conn = null;
					conn = (HttpURLConnection) new URL(url).openConnection();
					conn.setDoInput(true);
					conn.setDoOutput(false);
					conn.setUseCaches(true);
					conn.connect();
					in = conn.getInputStream();
					int readBytes = 0;
					while (true) {
						int length = in.read(out, readBytes, out.length - readBytes);
						if (length == -1)
							break;
						readBytes += length;
					}
					return readBytes;
				} catch (Exception ex) {
					return 0;
				} finally {
					System.out.println("finish download");
					StreamUtils.closeQuietly(in);
				}
			}

			@Override
			public void run() {
				byte[] bytes = new byte[400 * 2048]; // assuming the content
														// is
														// not bigger than
														// 200kb.
				int numBytes = download(bytes, url);
				System.out.println("download " + numBytes);
				if (numBytes != 0) {
					// load the pixmap, make it a power of two if necessary
					// (not
					// needed for GL ES 2.0!)
					Pixmap pixmap = new Pixmap(bytes, 0, numBytes);
					final int originalWidth = pixmap.getWidth();
					final int originalHeight = pixmap.getHeight();
					int width = MathUtils.nextPowerOfTwo(pixmap.getWidth());
					int height = MathUtils.nextPowerOfTwo(pixmap.getHeight());
					final Pixmap potPixmap = new Pixmap(width, height, pixmap.getFormat());
					potPixmap.drawPixmap(pixmap, 0, 0, 0, 0, pixmap.getWidth(), pixmap.getHeight());
					pixmap.dispose();
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							TextureRegion textureRegion = new TextureRegion(new Texture(potPixmap), 0, 0, originalWidth,
									originalHeight);
							setChanged();
							notifyObservers(textureRegion);

						}
					});
				}
			}
		}).start();
	}
}
