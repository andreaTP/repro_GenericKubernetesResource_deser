package org.test;

import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.WatchEvent;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.utils.Serialization;
import org.test.v2alpha1.crd.ExampleSpec;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Config config = new ConfigBuilder().build();
        KubernetesClient client = new DefaultKubernetesClient(config);

        var informer = client.genericKubernetesResources(
                ResourceDefinitionContext.fromResourceType(org.test.v1alpha1.crd.Example.class)
        ).runnableInformer(0);

        informer.addEventHandler(
                new ResourceEventHandler<>() {
                    @Override
                    public void onAdd(GenericKubernetesResource genericKubernetesResource) {
                        System.out.println("add");
                    }

                    @Override
                    public void onUpdate(GenericKubernetesResource genericKubernetesResource, GenericKubernetesResource t1) {
                        System.out.println("update");
                    }

                    @Override
                    public void onDelete(GenericKubernetesResource genericKubernetesResource, boolean b) {
                        System.out.println("delete");
                    }
                }
        );
        informer.run();

        Thread.sleep(1000);

        System.out.println("Accessing typed API");
        client.resources(org.test.v1alpha1.crd.Example.class).list().getItems();

        System.out.println("Creating CR");
        var res = Main.class.getResourceAsStream("/example-v2alpha1.yaml");
        client.load(res).inNamespace("default").createOrReplace();

        Thread.sleep(1000);
        client.close();
        System.exit(0);
    }
}
