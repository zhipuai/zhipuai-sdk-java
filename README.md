# [DEPRECATED] 智谱大模型开放接口SDK

⚠️ **此项目已废弃/停止维护！**

此项目已迁移至新地址：[MetaGLM智谱官方SDK](https://github.com/MetaGLM/zhipuai-sdk-java-v4)，MetaGLM是智谱官方维护的开源项目，致力于为大模型开发者提供全面的开发者工具、大模型知识体系以及基础和企业级的应用案例

Please use [MetaGLM SDK](https://github.com/MetaGLM/zhipuai-sdk-java-v4) instead. MetaGLM is an open-source project officially maintained by ZhipuAI, dedicated to providing comprehensive developer tools, large model knowledge systems, and both basic and enterprise-level application cases for large model developers.

## 简介
- <font color="red">**java sdk仍在开发测试阶段，有bug请留言联系**</font>
- 对所有接口进行了类型封装，无需查阅API文档即可完成接入
- 初始化client并调用成员函数，无需关注http调用过程的各种细节，所见即所得
- 默认缓存token
- 支持主流http client实现，已支持apache httpclient、okhttpclient
- 添加同步调用、去除自定义okhttp sse listener实现（meta解析优化）
- 修复同步调用500问题
- 支持returnType参数配置
- 支持ref参数配置
- 流式返回内容打印
- 支持maxToken参数配置
- 修复apiSecretKey分割bug
## 安装

- 运行环境：JDK1.8+
- maven坐标
```
        <dependency>
            <groupId>cn.bigmodel.openapi</groupId>
            <artifactId>oapi-java-sdk</artifactId>
            <version>1.0.6</version>
        </dependency>
```

## 使用
- 调用流程：
    1. 使用APISecretKey创建Client
    2. 调用Client对应的成员方法
- com.zhipu.oapi.demo有完整的demo示例，请替换自己的ApiKey和ApiSecret进行测试

### 创建Client

```
    //ClientV3 client = new ClientV3.Builder("{Your ApiKey}", "Your ApiSecret")
    //            .httpTransport(new ApacheHttpClientTransport())// 传输层默认使用okhttpclient，如果需要修改位其他http client（如apache），可以在这里指定。注意apache不支持sse调用
    //            .build();
    ClientV3 client = new ClientV3.Builder("{Your ApiSecretKey}")
                .httpTransport(new ApacheHttpClientTransport())// 传输层默认使用okhttpclient，如果需要修改位其他http client（如apache），可以在这里指定。注意apache不支持sse调用
                .build();       
```

### sse调用
```
        // 建议直接查看demo包代码，这里更新可能不及时
        ModelApiRequest modelApiRequest = new ModelApiRequest();
        modelApiRequest.setModelId(Constants.ModelChatGLM6B);
        modelApiRequest.setInvokeMethod(Constants.invokeMethodSse);
        // 可自定义sse listener
        StandardEventSourceListener listener = new StandardEventSourceListener();
        listener.setIncremental(false);
        modelApiRequest.setSseListener(listener);
        modelApiRequest.setIncremental(false);
        // 构建prompt
        ModelApiRequest.Prompt prompt = new ModelApiRequest.Prompt(ModelConstants.roleUser, "tell me something about C Ronaldo in English");
        List<ModelApiRequest.Prompt> prompts = new ArrayList<>();
        prompts.add(prompt);
        modelApiRequest.setPrompt(prompts);
        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());
        modelApiRequest.setRequestId(requestId);
        
        ModelApiResponse sseModelApiResp = client.invokeModelApi(sseModelApiRequest);
        System.out.println(String.format("call model api finished, method: %s", sseModelApiRequest.getInvokeMethod()));
        System.out.println(String.format("invoke api code: %d", sseModelApiResp.getCode()));
        System.out.println("model output:");
        System.out.println(sseModelApiResp.getData().getChoices().get(0).getContent());
        System.out.println("usage:");
        String usage = new Gson().toJson(sseModelApiResp.getData().getUsage(), Usage.class);
        System.out.println(usage);
        System.out.println("task_id: " + sseModelApiResp.getData().getTaskId());
```
