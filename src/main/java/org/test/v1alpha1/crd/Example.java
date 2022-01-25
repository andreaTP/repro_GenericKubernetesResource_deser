package org.test.v1alpha1.crd;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.ShortNames;
import io.fabric8.kubernetes.model.annotation.Version;

@Group("org.example")
@Version(value = "v1alpha1", storage = false)
@ShortNames("ex")
public class Example extends CustomResource<ExampleSpec, Void> implements Namespaced {

    @Override
    public String toString() {
        if (this.spec == null) {
            return "spec is null!!!";
        } else {
            return "{ \"foo\": " + this.spec.foo + ", \"bar\": " + this.spec.bar + " }";
        }
    }
}
