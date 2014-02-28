package input.output.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import file.operationinterfaces.InterfaceFileOperator;

public class FileOperation implements InterfaceFileOperator {

	private String dataString = null;// ���ò�������

	List<?> list = null;

	/*
	 * ���ļ�֮��д�����ݣ�filePath����д����ļ�·����
	 */
	public void inputFileTxtFormat(String filePath) throws IOException {

		File toFile = new File(filePath);

		// �����ж��ļ�·���Լ��ļ��Ƿ���ڣ����ǲ���Ļ�����ֱ�Ӵ����ļ�·��,֮��Ѱ�ң�����ֱ��ִ��д������

		if (toFile.getParentFile().exists()) {// ����ļ���·�����ڵĻ�
			FileWriter writer = new FileWriter(new File(filePath));
			writer.write(dataString);// ִ��д�������
			writer.flush();
			writer.close();
		} else {
			toFile.getParentFile().mkdir();
			FileWriter writer = new FileWriter(new File(filePath));
			writer.write(dataString);// ִ��д�������
			writer.flush();
			writer.close();
		}
	}

	/*
	 * ���ļ��ж�ȡ���ݣ�һ�ζ�ȡһ�У����ҽ����������ݱ������������ڹ����ڽӱ�
	 */
	public List<?> readDataFromFile(String filePath) throws IOException {

		List<String> lists = new ArrayList<String>();
		File file = new File(filePath);
		if (file.exists()) {// ����ļ����ڵĻ����ʹ��ж�ȡ��

			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					file));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				lists.add(line);
				// line = bufferedReader.readLine();
			}
			bufferedReader.close();

			list = lists;// ���Ƹ���������飻

			System.out.println("��������ļ��Ǵ��ڵģ�");

		} else {// �����ڿ���̨�ϴ�ӡ���ݲ����ڣ�
			System.out.println("��������ļ������ڣ�");
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
			String lineString = list.get(i);// ��ȡÿһ�е�����
			String[] lineStrings = lineString.split(" ");// ���������ݣ�
			int cols = lineStrings.length;
			for (int j = 0; j < cols; j++) {
				strings[i][j] = lineStrings[j];
			}
		}
		System.out.println("==========���¶����Կ�����Ķ�ά����Ĳ��ԣ���Ϊα����========");
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
