package file.operationinterfaces;

import java.io.IOException;
import java.util.List;

/**
 * ��װ��һЩ�ļ��Ĳ���������ֱ�ӽ���α���ݲ��ԣ�
 * 
 * @author zhaohongxu
 * 
 */
public interface InterfaceFileOperator {

	/**
	 * ��ָ�����ļ�·��filePath�ж�ȡ�ļ��Ķ�����,���ҽ���ȡ�����ݱ�����һ���б���
	 * 
	 * @param filePathָ�����ļ�·��
	 * @return
	 * @throws IOException
	 */
	public List<?> readDataFromFile(String filePath) throws IOException;

	/**
	 * д�����ݵ�ָ�����ļ�·��filePath�Ĳ���
	 * 
	 * @param filePath
	 *            ָ�����ļ�·��
	 * @throws IOException
	 */
	public void inputFileTxtFormat(String filePath,String[][]metrix) throws IOException;

	/**
	 * ��ȡ�ļ��е����ݣ����б����ʽ���ظ��û���һ�㲻ʹ�ã�
	 * 
	 * @return
	 */
	public List<?> getFileList();

	/**
	 * �����б��е����ݻ�ȡ��ά����
	 * 
	 * @param list����������б�
	 * @return ��ά����
	 */
	public String[][] getTwoDimensionalMatrix(List<String> list);

	/**
	 * �����ά�������Ϣ
	 * 
	 * @param metrix
	 *            ��Ҫ��ӡ�Ķ�ά������Ϣ
	 */
	public void printMetrix(String[][] metrix);
}
