package zhaokang.experiment.test;

import java.io.IOException;
import java.util.List;

import stacktest.recycle.Node;
import stacktest.recycle.NodeLink;
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
		fileOperation.printMetrix(metrix);
		System.out.println();

		int size = metrix.length;
		NodeLink nodeLink = new NodeLink(size);
		Node[] nodes = nodeLink.initialNodeLink(metrix);

		nodeLink.makeAllNodesFalse(nodes);

		for (int i = 1; i < size + 1; i++) {
			nodeLink.pushNodeToStack(i);
			nodeLink.makeAllNodesFalse(nodes);
		}
		nodeLink.printNodeLinkInfo(nodes);
	}

	// ���Զ�ά�ڽӱ����ҽ����е��ڽ���Ϣ��ӡ������̨�ϣ�
	// Graphic graphic = new Graphic();
	// graphic.initialGraphic(metrix);
	// graphic.makeAdjoinListAccordingToTwoMetrix(metrix);
	// System.out.println("������ڽ�����");
	// graphic.printGraphNodesLinkInfo(metrix);
	//
	// graphic.findAllRoutineAccordingToSpecifyNode("1");
	//
	// // ִ���������������������
	// // graphic.execDFSAccordingToAdjoinList();
	//
	// fileOperation.inputFileTxtFormat("D:/javaFileInput/tt.doc", metrix);

}