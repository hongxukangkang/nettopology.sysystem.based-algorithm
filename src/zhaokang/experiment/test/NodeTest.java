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

		// 获取文件，并将文件中的二维矩阵转化为二维数组；
		String filePath = "D:/javaFileInput/other.txt";// 指定读取文件的路径
		InterfaceFileOperator fileOperation = new FileOperation();
		List<String> list = (List<String>) fileOperation
				.readDataFromFile(filePath);
		String[][] metrix = fileOperation.getTwoDimensionalMatrix(list);

		// 测试二维邻接表，并且将其中的邻接信息打印到控制台上；
		Graphic graphic = new Graphic();
		graphic.initialGraphic(metrix);
		graphic.makeAdjoinListAccordingToTwoMetrix(metrix);
		System.out.println("输出该邻接链表。");
		graphic.printGraphNodesLinkInfo(metrix);

		// 执行深度优先搜索并输出结果
		graphic.execDFSAccordingToAdjoinList();

	}
}