package interview;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class Streams  {
    private enum Status {
        OPEN, CLOSED
    };

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }
    
    @Test
    public void StreamTest() throws IOException {
    	final Collection< Task > tasks = Arrays.asList(
    		new Task(Status.OPEN,5),	
    		new Task(Status.OPEN,13),	
    		new Task(Status.CLOSED,8)
    	);
    	
    	//在这个task集合中一共有多少个OPEN状态的点？
    	final long totalPointsOfOpenTasks = tasks
    			.stream()		//tasks集合转换成stream
    			.filter(task -> task.getStatus() == Status.OPEN)//过滤所有CIOSED的tasks
    			.mapToInt( Task::getPoints )	//基于每个task实例的Task::getPoints方法将task流转换成Integer集合
    			.sum();		//遍历stream
    	System.out.println("Total points:"+totalPointsOfOpenTasks);
    	
    	//创造性地支持并行处理（parallel processing）
    	//计算所有任务的点数之和
    	final double totalPoints = tasks
    			.stream()
    			.parallel()		//或parallelStream() parallel方法并行处理所有的task
    			.map(task -> task.getPoints()) //or map(Task::getPoints)
    			.reduce(0, Integer::sum);	//reduce方法计算最终的结果
    	System.out.println("Total points(all tasks):"+totalPoints);
    	
    	//根据状态进行分组
    	final Map<Status, List<Task>> map = tasks
    			.stream()
    			.collect(Collectors.groupingBy(Task::getStatus));
    	System.out.println(map);
    	
    	//计算整个集合中每个task分数的平均值
    	final Collection<String> result = tasks
    			.stream()										// Stream< String >
    			.mapToInt(Task::getPoints)						// IntStream
    			.asLongStream()									// LongStream
    			.mapToDouble(points1 -> points1 / totalPoints)	// DoubleStream
    			.boxed()										// Stream< Double >装箱操作
    			.mapToLong(weigth -> (long)(weigth * 100))		// LongStream
    			.mapToObj(percentage -> percentage + "%")		// Stream< String> 
    			.collect(Collectors.toList());					// List< String > 
    	System.out.println(result);
    	
    	//IO操作，从文本文件中逐行读取数据
    	String fileName = "H:/hongkun/code/settings-finance.xml";  
    	final Path path = new File( fileName ).toPath();
    	try( Stream< String > lines = Files.lines( path, StandardCharsets.UTF_8 ) ) {
    	    lines.onClose( () -> System.out.println("Done!") ).forEach( System.out::println );
    	}
    }
}