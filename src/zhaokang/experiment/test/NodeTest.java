package zhaokang.experiment.test;

import java.io.IOException;
import java.util.List;

import adjoin.list.Graphic;
import input.output.file.FileOperation;
import file.operationinterfaces.InterfaceFileOperator;

/**
 * @author zhaohongxu
 * 
 */
public class NodeTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		// ��ȡ�ļ��������ļ��еĶ�ά����ת��Ϊ��ά���飻
		String filePath = "D:/javaFileInput/other.txt";// ָ����ȡ�ļ���·��
		InterfaceFileOperator fileOperation = new FileOperation();
		List<String> list = (List<String>) fileOperation
				.readDataFromFile(filePath);
		String[][] metrix = fileOperation.getTwoDimensionalMatrix(list);

		// ���Զ�ά�ڽӱ����ҽ����е��ڽ���Ϣ��ӡ������̨�ϣ�
		Graphic graphic = new Graphic();
		graphic.initialGraphic(metrix);
		graphic.makeAdjoinListAccordingToTwoMetrix(metrix);
		System.out.println("������ڽ�����");
		graphic.printGraphNodesLinkInfo(metrix);

		// ִ���������������������
		graphic.execDFSAccordingToAdjoinList();

	}
}