package file.operationinterfaces;

import java.io.IOException;
import java.util.List;

/**
 * 封装了一些文件的操作，便于直接进行伪数据测试；
 * 
 * @author zhaohongxu
 * 
 */
public interface InterfaceFileOperator {

	/**
	 * 从指定的文件路径filePath中读取文件的读操作,并且将读取的数据保存在一个列表中
	 * 
	 * @param filePath指定的文件路径
	 * @return
	 * @throws IOException
	 */
	public List<?> readDataFromFile(String filePath) throws IOException;

	/**
	 * 写入数据到指定的文件路径filePath的操作
	 * 
	 * @param filePath
	 *            指定的文件路径
	 * @throws IOException
	 */
	public void inputFileTxtFormat(String filePath,String[][]metrix) throws IOException;

	/**
	 * 获取文件中的数据，以列表的形式返回给用户，一般不使用；
	 * 
	 * @return
	 */
	public List<?> getFileList();

	/**
	 * 根据列表中的数据获取二维矩阵
	 * 
	 * @param list保存参数的列表
	 * @return 二维矩阵
	 */
	public String[][] getTwoDimensionalMatrix(List<String> list);

	/**
	 * 输出二维矩阵的信息
	 * 
	 * @param metrix
	 *            需要打印的二维矩阵信息
	 */
	public void printMetrix(String[][] metrix);
}
