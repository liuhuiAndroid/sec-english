1. 多渠道打包

   ```groovy
   // 风味维度
   flavorDimensions 'environment'
   // 产品风味
   productFlavors {
       alpha {
           dimension 'environment'
           // 区分环境：测试环境地址
           buildConfigField 'String', 'BASE_URL', "\"http://localhost:8080/\""
       }
       beta {
           dimension 'environment'
           // 区分环境：开发环境地址
           buildConfigField 'String', 'BASE_URL', "\"http://localhost:8080/\""
       }
   }
   ```

2. 签名配置

   ```groovy
   // 签名信息
   signingConfigs {
       release {
           storeFile file('../sec-english.jks')
           storePassword "123456"
           keyAlias "english"
           keyPassword "123456"
       }
       debug {
           storeFile file('../sec-english.jks')
           storePassword "123456"
           keyAlias "english"
           keyPassword "123456"
       }
   }
   ```

3. 打包名称配置

   ```groovy
   // 更改apk打包后的名称
   android.applicationVariants.all { variant ->
       variant.outputs.all {
           // 默认生成到app目录下，修改路径到app/build/apk下
           def dir = new File(project.buildDir.absolutePath + "/apk")
           variant.getPackageApplicationProvider().get().outputDirectory = dir
           def date = new Date().format("yyyyMMdd_HH-mm", TimeZone.getTimeZone("GMT+08"))
           def variantName = variant.name.replace('Release', '')
           outputFileName = "app-${variantName}-${variant.versionName}-${date}.apk"
       }
   }
```
   
   