package vu.lt;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;

@ManagedBean
@ApplicationScoped
@Model
public class Data {

    public Enums.Color[] getColors() {
        return Enums.Color.values();
    }

    public Enums.Type[] getTypes() {
        return Enums.Type.values();
    }
}