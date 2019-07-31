import java.util.*;
import java.util.function.BiConsumer;

public class WordsNumber {

	public static void main(String[] args) {
		String str = "[Home]\r\n" + 
				"\r\n" + 
				"Publications\r\n" + 
				"\r\n" + 
				"Journal and Magazine Papers\r\n" + 
				"\r\n" + 
				"Weike Pan, Qiang Yang*, Yuchao Duan, Ben Tan and Zhong Ming*. \r\n" + 
				"Transfer Learning for Behavior Ranking [J]. \r\n" + 
				"ACM Transactions on Intelligent Systems and Technology (2157-6904), 8(5):65:1-65:23, July 2017. ESI (Computer Science).\r\n" + 
				"paper, slides, code\r\n" + 
				"Weike Pan and Zhong Ming*. \r\n" + 
				"Collaborative Recommendation with Multiclass Preference Context [J]. \r\n" + 
				"IEEE Intelligent Systems (1541-1672), 32(2):45-51, March 2017. ESI (Engineering).\r\n" + 
				"paper, slides, code, bibtex\r\n" + 
				"Mengsi Liu#, Weike Pan#, Miao Liu, Yaofeng Chen, Xiaogang Peng* and Zhong Ming*. (#:co-first author) \r\n" + 
				"Mixed Similarity Learning for Recommendation with Implicit Feedback [J]. \r\n" + 
				"Knowledge-Based Systems (0950-7051), 119:178–185, 1 March 2017. ESI (Computer Science).\r\n" + 
				"paper, slides, code, data, bibtex\r\n" + 
				"Weike Pan and Li Chen.\r\n" + 
				"Group Bayesian Personalized Ranking with Rich Interactions for One-Class Collaborative Filtering [J]. Extended from our IJCAI 2013 paper.\r\n" + 
				"Neurocomputing (0925-2312), 207:501-510, 26 September 2016. ESI (Computer Science).\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan, Qiang Yang*, Yuchao Duan and Zhong Ming*.\r\n" + 
				"Transfer Learning for Semi-Supervised Collaborative Recommendation [J]. \r\n" + 
				"ACM Transactions on Interactive Intelligent Systems (2160-6455), 6(2):10:1-10:21, July 2016. ESI (Computer Science). \r\n" + 
				"paper, slides, code, bibtex\r\n" + 
				"Weike Pan, Mengsi Liu and Zhong Ming*.\r\n" + 
				"Transfer Learning for Heterogeneous One-Class Collaborative Filtering [J]. \r\n" + 
				"IEEE Intelligent Systems (1541-1672), 31(4):43-49, July 2016. ESI (Engineering).\r\n" + 
				"paper, slides, code, data, bibtex\r\n" + 
				"Weike Pan and Qiang Yang.\r\n" + 
				"Transfer Learning for Behavior Prediction [J]. Invited Paper for Trends & Controversies on Uncovering and Predicting Human Behaviors.\r\n" + 
				"IEEE Intelligent Systems (1541-1672), 31(2):86-88, March 2016. ESI (Engineering).\r\n" + 
				"paper\r\n" + 
				"Weike Pan, Shanchuan Xia, Zhuode Liu, Xiaogang Peng and Zhong Ming*. \r\n" + 
				"Mixed Factorization for Collaborative Recommendation with Heterogeneous Explicit Feedbacks [J].\r\n" + 
				"Information Sciences (0020-0255), 332:84–93, 1 March 2016. ESI (Computer Science).\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan.\r\n" + 
				"A Survey of Transfer Learning for Collaborative Recommendation with Auxiliary Data [J]. Extended from part of my Ph.D. thesis.\r\n" + 
				"Neurocomputing (0925-2312), 177:447–453, 12 February 2016. ESI (Computer Science).\r\n" + 
				"paper, slides, bibtex\r\n" + 
				"Weike Pan, Zhuode Liu, Zhong Ming*, Hao Zhong, Xin Wang and Congfu Xu. \r\n" + 
				"Compressed Knowledge Transfer via Factorization Machine for Heterogeneous Collaborative Recommendation [J]. \r\n" + 
				"Knowledge-Based Systems (0950-7051), 85:234–244, September 2015. ESI (Computer Science).\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan, Hao Zhong, Congfu Xu* and Zhong Ming. \r\n" + 
				"Adaptive Bayesian Personalized Ranking for Heterogeneous Implicit Feedbacks [J]. \r\n" + 
				"Knowledge-Based Systems (0950-7051), 73:173–180, January 2015. ESI (Computer Science).\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan and Zhong Ming*. \r\n" + 
				"Interaction-Rich Transfer Learning for Collaborative Filtering with Heterogeneous User Feedback [J]. \r\n" + 
				"IEEE Intelligent Systems (1541-1672), 29(6):48-54, December 2014. ESI (Engineering).\r\n" + 
				"paper, bibtex\r\n" + 
				"Congfu Xu, Baojun Su, Yunbiao Cheng, Weike Pan* and Li Chen. \r\n" + 
				"An Adaptive Fusion Algorithm for Spam Detection [J].\r\n" + 
				"IEEE Intelligent Systems (1541-1672), 29(4):2-8, August 2014. ESI (Engineering).\r\n" + 
				"paper, bibtex\r\n" + 
				"Yuanchun Zhou, Mingjie Tang, Weike Pan, Jinyan Li*, Weihang Wang, Jing Shao, Liang Wu, Jianhui Li, Qiang Yang and Baoping Yan. \r\n" + 
				"Bird Flu Outbreak Prediction via Satellite Tracking [J]. \r\n" + 
				"IEEE Intelligent Systems (1541-1672), 29(4):10-17, August 2014. ESI (Engineering).\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan and Qiang Yang*. \r\n" + 
				"Transfer Learning in Heterogeneous Collaborative Filtering Domains [J]. \r\n" + 
				"Artificial Intelligence, 197:39–55, April 2013. ESI (Computer Science).\r\n" + 
				"paper, code, bibtex\r\n" + 
				"Wenliang Zhong, Weike Pan, James T. Kwok and Ivor W. Tsang. \r\n" + 
				"Incorporating the Loss Function into Discriminative Clustering of Structured Outputs [J]. \r\n" + 
				"IEEE Transactions on Neural Networks, 21(10):1564-1575, October 2010. ESI (Computer Science).\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan and Congfu Xu*. \r\n" + 
				"BioASLM: an Efficient Biology-Inspired Acoustic Source Localization Model in Indoor Sensor Networks [J]. \r\n" + 
				"Journal of Electronics (China), 16(3):406-410, July 2007. \r\n" + 
				"Conference Papers\r\n" + 
				"\r\n" + 
				"Miao Liu, Zijie Zeng, Weike Pan, Xiaogang Peng*, Zhiguang Shan and Zhong Ming*. \r\n" + 
				"Hybrid One-Class Collaborative Filtering for Job Recommendation. \r\n" + 
				"In Proceedings of International Conference on Smart Computing and Communication (SmartCom 2016), Shenzhen, China, pages 267-276, December 17th-19th, 2016. EI.\r\n" + 
				"paper, code, bibtex\r\n" + 
				"Feng Wang, Li Chen and Weike Pan. \r\n" + 
				"Where to Place Your Next Restaurant? Optimal Restaurant Placement via Leveraging User-Generated Reviews [C]. (Short Paper) \r\n" + 
				"In: Proceedings of the 25rd ACM International Conference on Information and Knowledge Management (CIKM 2016). Indianapolis, USA, pages 2371-2376, October 24-28, 2016. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Hao Zhong, Weike Pan, Congfu Xu*, Zhi Yin and Zhong Ming. \r\n" + 
				"Adaptive Pairwise Preference Learning for Collaborative Recommendation with Implicit Feedbacks [C]. (Short Paper) \r\n" + 
				"In: Proceedings of the 23rd ACM International Conference on Information and Knowledge Management (CIKM 2014). Shanghai, China, pages 1999-2002, November 3-7, 2014. EI. \r\n" + 
				"paper, bibtex\r\n" + 
				"Xin Wang, Weike Pan and Congfu Xu*. \r\n" + 
				"HGMF: Hierarchical Group Matrix Factorization for Collaborative Recommendation [C]. \r\n" + 
				"In: Proceedings of the 23rd ACM International Conference on Information and Knowledge Management (CIKM 2014). Shanghai, China, pages 769-778, November 3-7, 2014. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan and Li Chen. \r\n" + 
				"GBPR: Group Preference based Bayesian Personalized Ranking for One-Class Collaborative Filtering [C]. \r\n" + 
				"In: Proceedings of the 23rd International Joint Conference on Artificial Intelligence (IJCAI 2013). Beijing, China, pages 2691-2697, August 3-9, 2013. EI.\r\n" + 
				"paper, code, data, bibtex\r\n" + 
				"Feng Wang, Weike Pan and Li Chen. \r\n" + 
				"Recommendation for New Users with Partial Preferences by Integrating Product Reviews with Static Speci?cations [C]. \r\n" + 
				"In: Proceedings of the 21st Conference on User Modeling, Adaptation and Personalization (UMAP 2013). Rome, Italy, pages 281-288, June 10-14, 2013. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan and Li Chen. \r\n" + 
				"CoFiSet: Collaborative Filtering via Learning Pairwise Preferences over Item-sets [C]. \r\n" + 
				"In: Proceedings of the SIAM International Conference on Data Mining (SDM 2013). Austin, Texas, USA, pages 180-188, May 2-4, 2013. EI.\r\n" + 
				"Nominee of Best Paper Award\r\n" + 
				"paper, bibtex\r\n" + 
				"Zhongqi Lu, Erheng Zhong, Lili Zhao, Evan Xiang, Weike Pan and Qiang Yang. \r\n" + 
				"Selective Transfer Learning for Cross Domain Recommendation [C]. \r\n" + 
				"In: Proceedings of the SIAM International Conference on Data Mining (SDM 2013). Austin, Texas, USA, pages 641-649, May 2-4, 2013. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan, Evan W. Xiang and Qiang Yang. \r\n" + 
				"Transfer Learning in Collaborative Filtering with Uncertain Ratings [C]. \r\n" + 
				"In: Proceedings of the 26th AAAI Conference on Artificial Intelligence (AAAI 2012). Toronto, Ontario, Canada, pages 662-668, July 22-26, 2012. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan, Nathan N. Liu, Evan W. Xiang and Qiang Yang. \r\n" + 
				"Transfer Learning to Predict Missing Ratings via Heterogeneous User Feedbacks [C]. \r\n" + 
				"In: Proceedings of the 22rd International Joint Conference on Artificial Intelligence (IJCAI 2011). Barcelona, Catalonia, Spain, pages 2318-2323, July 16-22, 2011. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Evan W. Xiang, Sinno Jialin Pan, Weike Pan, Jian Su and Qiang Yang. \r\n" + 
				"Source-Selection-Free Transfer Learning [C]. \r\n" + 
				"In: Proceedings of the 22rd International Joint Conference on Artificial Intelligence (IJCAI 2011). Barcelona, Catalonia, Spain, pages 2355-2360, July 16-22, 2011. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan and James T. Kwok. \r\n" + 
				"Structured Clustering with Automatic Kernel Adaptation [C]. \r\n" + 
				"In: Proceedings of the 2011 International Joint Conference on Neural Networks (IJCNN 2011). San Jose, California, USA, pages 1322-1327, July 31-August 5, 2011. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Weike Pan, Evan W. Xiang, Nathan N. Liu and Qiang Yang. \r\n" + 
				"Transfer Learning in Collaborative Filtering for Sparsity Reduction [C]. \r\n" + 
				"In: Proceedings of the 24th AAAI Conference on Artificial Intelligence (AAAI 2010). Atlanta, Georgia, USA, pages 230-235, July 11-15, 2010. EI.\r\n" + 
				"paper, code, bibtex\r\n" + 
				"Chonghai Hu, James T. Kwok and Weike Pan. \r\n" + 
				"Accelerated Gradient Methods for Stochastic Optimization and Online Learning [C]. \r\n" + 
				"In: Proceedings of the 23rd Annual Conference on Neural Information Processing Systems (NIPS 2009), Vancouver, Canada, pages 781-789, December 7-10, 2009. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Xi Chen, Weike Pan, James T. Kwok and Jaime Carbonell. \r\n" + 
				"Accelerated Gradient Method for Multi-Task Sparse Learning Problem [C]. \r\n" + 
				"In: Proceedings of the 9th International Conference on Data Mining (ICDM 2009), Miami, Florida, USA, pages 746-751, December 6-9, 2009. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Shijian Li, Congfu Xu, Weike Pan and Yunhe Pan. \r\n" + 
				"Sensor Deployment Optimization for Detecting Maneuvering Targets [C]. \r\n" + 
				"In: Proceedings of the 8th International Conference on Information Fusion (Fusion 2005), Philadelphia, PA, USA, pages 1629-1635, July 25-29, 2005. EI.\r\n" + 
				"Workshop Papers\r\n" + 
				"\r\n" + 
				"Jixiong Liu, Jiakun Shi, Wanling Cai, Bo Liu, Weike Pan, Qiang Yang* and Zhong Ming*. \r\n" + 
				"Transfer Learning from APP Domain to News Domain for Dual Cold-Start Recommendation. (Short Paper) \r\n" + 
				"In: Proceedings of the 1st Workshop on Intelligent Recommender Systems by Knowledge Transfer & Learning (RecSysKTL 2017) co-located with the 11th ACM Conference on Recommender Systems (RecSys 2017), Como, Italy, August 27-31, 2017. EI.\r\n" + 
				"paper, slides, video presentation (YouTube), bibtex\r\n" + 
				"Xinchao Chen, Weike Pan* and Zhong Ming*. \r\n" + 
				"TOCCF: Time-Aware One-Class Collaborative Filtering. (Short Paper) \r\n" + 
				"In: Proceedings of the Workshop on Multi-dimensional Information Fusion for User Modeling and Personalization (IFUP 2016) co-located with the 24th Conference on User Modeling, Adaptation and Personalization (UMAP 2016), Halifax, Canada, July 13-17, 2016. EI.\r\n" + 
				"paper, code, bibtex\r\n" + 
				"Xiaogang Peng, Yaofeng Chen, Yuchao Duan, Weike Pan* and Zhong Ming*. \r\n" + 
				"RBPR: Role-based Bayesian Personalized Ranking for Heterogeneous One-Class Collaborative Filtering. (Short Paper) \r\n" + 
				"In: Proceedings of the Workshop on Multi-dimensional Information Fusion for User Modeling and Personalization (IFUP 2016) co-located with the 24th Conference on User Modeling, Adaptation and Personalization (UMAP 2016), Halifax, Canada, July 13-17, 2016. EI.\r\n" + 
				"paper, bibtex\r\n" + 
				"Edited Special Issue\r\n" + 
				"\r\n" + 
				"Weike Pan, Qiang Yang, Charu Aggarwal and Christoph Koch. \r\n" + 
				"Big Data.\r\n" + 
				"Guest Editors' Introduction, Special Issue on Big Data, IEEE Intelligent Systems (1541-1672), 32(2):7-8, March 2017.\r\n" + 
				"paper, bibtex\r\n" + 
				"Edited Workshop Proceeding\r\n" + 
				"\r\n" + 
				"Yong Zheng, Weike Pan, Shaghayegh (Sherry) Sahebi, Ignacio Fernández.\r\n" + 
				"The 1st Workshop on Intelligent Recommender Systems by Knowledge Transfer & Learning (RecSysKTL).\r\n" + 
				"Workshop Editorial. In: Proceedings of the 11th ACM Conference on Recommender Systems (RecSys 2017), Como, Italy, Aug 27-31, 2017.\r\n" + 
				"paper, bibtex\r\n" + 
				"Book Chapter\r\n" + 
				"\r\n" + 
				"Weike Pan, Erheng Zhong and Qiang Yang. \r\n" + 
				"Transfer Learning for Text Mining [M].\r\n" + 
				"Mining Text Data, Charu C. Aggarwal and Chengxiang Zhai, Springer, pages 223-257, Germany 2012.\r\n" + 
				"Ph.D. Thesis\r\n" + 
				"\r\n" + 
				"Weike Pan. \r\n" + 
				"Transfer Learning in Collaborative Filtering. \r\n" + 
				"PhD Thesis, Hong Kong University of Science and Technology, June 2012.\r\n" + 
				"thesis\r\n" + 
				"Translation\r\n" + 
				"\r\n" + 
				"Iván Cantador, Ignacio Fernández-Tobías, Shlomo Berkovsky and Paolo Cremonesi. Cross-Domain Recommender Systems. Chapter 27 of Recommender Systems Handbook (Second Edition), Springer, 2015.\r\n" + 
				"翻译: 潘微科,曾子杰. 跨领域推荐系统. 《推荐系统：技术、评估及高效算法》第27章, 机械工业出版社, 2016.\r\n" + 
				"slides\r\n" + 
				"Last change: September 12, 2017";
		
		String regex = "[[^x00-xff]\\s\\d\\p{Punct}]+";
		String[] words = str.split(regex);
		HashMap<String,Integer> wordslist = new HashMap<> ();

		for(int i=0;i<words.length;i++)
		{
			if(wordslist.containsKey(words[i]))
			{
				int temp = wordslist.get(words[i]);
				wordslist.put(words[i], temp+1);
			}
			else
				wordslist.put(words[i],1);
		}
		wordslist.remove("");
		
		wordslist.forEach(new BiConsumer<String,Integer>()
		{
			public void accept(String k,Integer v)
			{
				System.out.println(k+": "+v);
			}
		});
	}

}
