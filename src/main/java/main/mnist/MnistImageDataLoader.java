package main.mnist;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class MnistImageDataLoader {

	private static final String TRAIN="mnist_png/train/";
	private static final String TEST="mnist_png/test/";
	
	

	public Map<String, List<D1Image>> loadTestData() throws IOException {		      
		Map<String, List<D1Image>> result = new HashMap<String, List<D1Image>>();
		for(int i=0;i<10;i++) {
			List<D1Image> images=load(TEST,""+i);
			result.put(""+i, images);
		}
		return result;
	}

	public Map<String, List<D1Image>> loadTrainData() throws IOException {		      
		Map<String, List<D1Image>> result = new HashMap<String, List<D1Image>>();
		for(int i=0;i<10;i++) {
			List<D1Image> images=load(TRAIN,""+i);
			result.put(""+i, images);
		}
		return result;
	}


	private List<D1Image> load(String trainOrTestFolder, String digit) throws IOException {
		List<D1Image> result = new ArrayList();
		File folder=new File(trainOrTestFolder+digit);
		File[] fileList=folder.listFiles();
		for(File f: fileList) {
			int[][] imageData = readImage(f);
			result.add(new D1Image(imageData));
		}
		return result;
	}


	private int[][] readImage(File file) throws IOException {
		BufferedImage img=ImageIO.read(file);
		int width=img.getWidth();
		int height=img.getHeight();
		
		int[][] imgarr=new int[width][height];
		Raster raster=img.getData();
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				imgarr[i][j] = raster.getSample(i, j, 0);
			}
		}
		return imgarr;
	}
	
}
