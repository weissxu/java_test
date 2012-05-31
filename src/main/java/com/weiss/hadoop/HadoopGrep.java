package com.weiss.hadoop;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.IdentityReducer;

public class HadoopGrep {

	public static class RegMapper extends MapReduceBase implements Mapper {

		private Pattern pattern;

		@Override
		public void configure(JobConf job) {
			pattern = Pattern.compile(job.get(" mapred.mapper.regex "));
		}

		@Override
		public void map(Object key, Object value, @SuppressWarnings("rawtypes") OutputCollector output,
				Reporter reporter) throws IOException {
			String text = ((Text) value).toString();
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				output.collect(key, value);
			}
		}
	}

	private HadoopGrep() {
	} // singleton

	public static void main(String[] args) throws Exception {

		JobConf grepJob = new JobConf(HadoopGrep.class);
		grepJob.setJobName(" grep-search ");
		grepJob.set(" mapred.mapper.regex ", "hadoop");

		FileInputFormat.setInputPaths(grepJob, "input");
		FileOutputFormat.setOutputPath(grepJob, new Path("output"));
		grepJob.setMapperClass(RegMapper.class);
		grepJob.setReducerClass(IdentityReducer.class);

		JobClient.runJob(grepJob);
	}
}