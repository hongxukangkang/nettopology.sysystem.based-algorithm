package input.output.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import file.operationinterfaces.InterfaceFileOperator;

public class FileOperation implements InterfaceFileOperator {

	private String dataString = null;// 设置测试数据

	List<?> list = null;

	/*
	 * 向文件之内写入数据，filePath代表写入的文件路径；
	 */
	public void inputFileTxtFormat(String filePath, String[][] metrix)
			throws IOException {

		File toFile = new File(filePath);
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;

		// 首先判定文件路径以及文件是否存在，若是不存的话，则直接创建文件路径,之后寻找；否则直接执行写操作；

		if (!toFile.exists()) {// 如果文件的路径存在的话
			toFile.createNewFile();
		}

		fos = new FileOutputStream(toFile);
		osw = new OutputStreamWriter(fos);
		bw = new BufferedWriter(osw);
		int line = metrix.length;
		int cols = metrix[0].length;
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < cols; j++) {
				if (j != (cols - 1)) {
					bw.write(metrix[i][j] + "  ");
				} else {
					bw.write(metrix[i][j] + "\n");
				}
			}
		}

		if (bw != null) {
			bw.close();
			bw = null;
		}
		if (osw != null) {
			osw.close();
			osw = null;
		}
		if (fos != null) {
			fos.close();
			fos = null;
		}

	}

	/*
	 * 从文件中读取数据，一次读取一行，并且将读到的数据保存起来，用于构造邻接表
	 */
	public List<?> readDataFromFile(String filePath) throws IOException {

		List<String> lists = new ArrayList<String>();
		File file = new File(filePath);
		if (file.exists()) {// 如果文件存在的话，就从中读取；

			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					file));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				lists.add(line);
				// line = bufferedReader.readLine();
			}
			bufferedReader.close();

			list = lists;// 复制给定义的数组；

			System.out.println("您输入的文件是存在的！");

		} else {// 否则，在控制台上打印数据不存在；
			System.out.println("您输入的文件不存在！");
		}

		return list;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}

	@Override
	public List<?> getFileList() {
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * file.operationinterfaces.InterfaceFileOperator#getTwoDimensionalMatrix
	 * (java.util.List)
	 */
	@Override
	public String[][] getTwoDimensionalMatrix(List<String> list) {
		int param = list.size();
		String[][] strings = new String[param][param];

		for (int i = 0; i < param; i++) {
			String lineString = list.get(i);// 获取每一行的数据
			String[] lineStrings = lineString.split(" ");// 代表行数据；
			int cols = lineStrings.length;
			for (int j = 0; j < cols; j++) {
				strings[i][j] = lineStrings[j];
			}
		}
		System.out.println("==========以下都是赵康构造的二维矩阵的测试，皆为伪数据========");
		System.out.println();
		return strings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * file.operationinterfaces.InterfaceFileOperator#printMetrix(java.lang.
	 * String[][])
	 */
	@Override
	public void printMetrix(String[][] metrix) {
		System.out.println("The information of metrix is as follows");
		int line = metrix.length;
		int cols = metrix[0].length;
		for (int i = 0; i < line; i++) {
			System.out.print("The " + (i + 1)
					+ "th information of metrix is:-->");
			for (int j = 0; j < cols; j++) {
				System.out.print(metrix[i][j] + "  ");
			}
			System.out.println();
		}

	}

}
