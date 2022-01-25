
To reproduce the error, with an available cluster you can run:

```bash
mvn clean compile

kubectl apply -f target/classes/META-INF/fabric8/examples.org.example-v1.yml

mvn clean compile exec:java
```

StackTrace:

```
2022-01-25 14:42:22,600 ERROR [AbstractWatchManager] - Unhandled exception encountered in watcher event handler
io.fabric8.kubernetes.client.KubernetesClientException: An error has occurred.
        at io.fabric8.kubernetes.client.KubernetesClientException.launderThrowable(KubernetesClientException.java:103)
        at io.fabric8.kubernetes.client.KubernetesClientException.launderThrowable(KubernetesClientException.java:97)
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:319)
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:240)
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:226)
        at io.fabric8.kubernetes.client.dsl.internal.AbstractWatchManager.readWatchEvent(AbstractWatchManager.java:237)
        at io.fabric8.kubernetes.client.dsl.internal.AbstractWatchManager.onMessage(AbstractWatchManager.java:262)
        at io.fabric8.kubernetes.client.dsl.internal.WatcherWebSocketListener.onMessage(WatcherWebSocketListener.java:68)
        at io.fabric8.kubernetes.client.okhttp.OkHttpWebSocketImpl$BuilderImpl$1.onMessage(OkHttpWebSocketImpl.java:96)
        at okhttp3.internal.ws.RealWebSocket.onReadMessage(RealWebSocket.java:323)
        at okhttp3.internal.ws.WebSocketReader.readMessageFrame(WebSocketReader.java:219)
        at okhttp3.internal.ws.WebSocketReader.processNextFrame(WebSocketReader.java:105)
        at okhttp3.internal.ws.RealWebSocket.loopReader(RealWebSocket.java:274)
        at okhttp3.internal.ws.RealWebSocket$2.onResponse(RealWebSocket.java:214)
        at okhttp3.RealCall$AsyncCall.execute(RealCall.java:203)
        at okhttp3.internal.NamedRunnable.run(NamedRunnable.java:32)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
        at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `int` from String "hello": not a valid `int` value
 at [Source: UNKNOWN; byte offset: #UNKNOWN] (through reference chain: io.fabric8.kubernetes.api.model.WatchEvent["object"]->org.test.v1alpha1.crd.Example["spec"]->org.test.v1alpha1.crd.ExampleSpec["foo"])
        at com.fasterxml.jackson.databind.exc.InvalidFormatException.from(InvalidFormatException.java:67)
        at com.fasterxml.jackson.databind.DeserializationContext.weirdStringException(DeserializationContext.java:1991)
        at com.fasterxml.jackson.databind.DeserializationContext.handleWeirdStringValue(DeserializationContext.java:1219)
        at com.fasterxml.jackson.databind.deser.std.StdDeserializer._parseIntPrimitive(StdDeserializer.java:768)
        at com.fasterxml.jackson.databind.deser.std.StdDeserializer._parseIntPrimitive(StdDeserializer.java:747)
        at com.fasterxml.jackson.databind.deser.std.NumberDeserializers$IntegerDeserializer.deserialize(NumberDeserializers.java:529)
        at com.fasterxml.jackson.databind.deser.std.NumberDeserializers$IntegerDeserializer.deserialize(NumberDeserializers.java:506)
        at com.fasterxml.jackson.databind.deser.impl.FieldProperty.deserializeAndSet(FieldProperty.java:138)
        at io.fabric8.kubernetes.client.utils.serialization.SettableBeanPropertyDelegate.deserializeAndSet(SettableBeanPropertyDelegate.java:131)
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:313)
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:176)
        at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:129)
        at io.fabric8.kubernetes.client.utils.serialization.SettableBeanPropertyDelegate.deserializeAndSet(SettableBeanPropertyDelegate.java:131)
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:313)
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:176)
        at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:322)
        at com.fasterxml.jackson.databind.ObjectMapper._readValue(ObjectMapper.java:4650)
        at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:2831)
        at com.fasterxml.jackson.databind.ObjectMapper.treeToValue(ObjectMapper.java:3295)
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.fromObjectNode(KubernetesDeserializer.java:99)
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.deserialize(KubernetesDeserializer.java:63)
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.deserialize(KubernetesDeserializer.java:45)
        at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:129)
        at io.fabric8.kubernetes.client.utils.serialization.SettableBeanPropertyDelegate.deserializeAndSet(SettableBeanPropertyDelegate.java:131)
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:313)
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:176)
        at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:322)
        at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4674)
        at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3674)
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:317)
        ... 16 common frames omitted
```
