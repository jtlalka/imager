# Imager

Console tool for measuring distance and defining geographical direction
between consecutive GPS locations obtained from images metadata.
This tool was made as an example of usage of **Kotlin language**
with **reactive extensions RxJava**.

#### Build project
```
mvn install
```

#### Run project
```
java -jar target/imager-1.0-jar-with-dependencies.jar /images_dir/
```

#### Example output
```
IMG_0116.JPG			 Dist:     0,00		 Cord:      0  (   )
IMG_0117.JPG			 Dist:     6,51		 Cord:    269  ( W )
IMG_0118.JPG			 Dist:     8,37		 Cord:    270  ( W )
IMG_0119.JPG			 Dist:     7,99		 Cord:    270  ( W )
IMG_0120.JPG			 Dist:     7,94		 Cord:    269  ( W )
IMG_0121.JPG			 Dist:     7,84		 Cord:    270  ( W )
IMG_0122.JPG			 Dist:     7,80		 Cord:    270  ( W )
IMG_0123.JPG			 Dist:     7,91		 Cord:    269  ( W )
IMG_0124.JPG			 Dist:     7,84		 Cord:    270  ( W )
IMG_0125.JPG			 Dist:     7,87		 Cord:    270  ( W )
IMG_0126.JPG			 Dist:     7,79		 Cord:    269  ( W )
IMG_0127.JPG			 Dist:     7,84		 Cord:    269  ( W )
IMG_0128.JPG			 Dist:     7,82		 Cord:    270  ( W )
IMG_0129.JPG			 Dist:     7,89		 Cord:    270  ( W )
IMG_0130.JPG			 Dist:     7,78		 Cord:    270  ( W )
IMG_0131.JPG			 Dist:     7,83		 Cord:    270  ( W )
IMG_0132.JPG			 Dist:     4,10		 Cord:    273  ( W )
IMG_0133.JPG			 Dist:     4,04		 Cord:    352  ( N )
IMG_0134.JPG			 Dist:     8,14		 Cord:      0  ( N )
IMG_0135.JPG			 Dist:     2,82		 Cord:     21  ( N )
IMG_0136.JPG			 Dist:     6,28		 Cord:     91  ( E )
IMG_0137.JPG			 Dist:     7,99		 Cord:     93  ( E )
IMG_0138.JPG			 Dist:     7,83		 Cord:     91  ( E )
IMG_0139.JPG			 Dist:     7,85		 Cord:     90  ( E )
IMG_0140.JPG			 Dist:     7,86		 Cord:     90  ( E )
IMG_0141.JPG			 Dist:     7,81		 Cord:     90  ( E )
IMG_0142.JPG			 Dist:     7,85		 Cord:     90  ( E )
IMG_0143.JPG			 Dist:     7,78		 Cord:     90  ( E )
IMG_0144.JPG			 Dist:     7,85		 Cord:     90  ( E )
IMG_0145.JPG			 Dist:     7,78		 Cord:     90  ( E )
IMG_0146.JPG			 Dist:     7,93		 Cord:     89  ( E )
IMG_0147.JPG			 Dist:     7,89		 Cord:     90  ( E )
IMG_0148.JPG			 Dist:     7,86		 Cord:     90  ( E )
IMG_0149.JPG			 Dist:     7,85		 Cord:     90  ( E )
IMG_0150.JPG			 Dist:     7,85		 Cord:     90  ( E )
IMG_0151.JPG			 Dist:     4,37		 Cord:     93  ( E )
```