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

		// 获取文件，并将文件中的二维矩阵转化为二维数组；
		String filePath = "D:/javaFileInput/topology.txt";// 指定读取文件的路径
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
		System.out.println(nodeLink.getNumber());
	}
}