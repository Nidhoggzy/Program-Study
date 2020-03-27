# 实验十一
## 输入输出应用

    * 数据解析和统计
        * 使用Java语言读取解压后的文件（Jewelry.txt）
            * product.txt：每行三列，以分号作为分隔符，第一列是productID，第二列是title，第三列是price；表示该product的ID、title和price
            * user.txt：每行两列，以分号作为分隔符，第一列是userID，第二列是profileName；表示该user的ID和name
            * review.txt：每行三列，以分号作为分隔符，第一列是userID，第二列是productID，第三列是summary；表示(user, product, summary)三元组
        * 使用Java语言对score.txt进行统计
            * productAvg.txt：每行两列，以分号作为分隔符，第一列是productID，第二列是所有用户对该product的平均score，表示该product的受欢迎程度
            * userAvg.txt：每行两列，以分号作为分隔符，第一列是userID，第二列是该user对所有product的平均score，表示该用户打分的高低
