package br.com.senai.sout.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
public class SpringBean {

    private String usuario;
    private final FacesContext fc = FacesContext.getCurrentInstance();
    private final ExternalContext ec = fc.getExternalContext();
    private String url = FacesContext.getCurrentInstance().getExternalContext().
            getRequestContextPath();

    private final List<String> permissoes = new ArrayList<>();

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public SpringBean() throws IOException {
        usuario = SecurityContextHolder.getContext().getAuthentication().
                getName();

        Authentication auth = SecurityContextHolder.
                getContext().getAuthentication();

        url = FacesContext.getCurrentInstance().
                getExternalContext().getRequestContextPath();

        /**
         * Aqui eu redireciono de acordo com o perfil de pessoa.
         */
        ec.redirect(redirecionar(auth));
    }

    private String redirecionar(Authentication auth) {
        for (Object o : auth.getAuthorities()) {
            permissoes.add(o.toString());
        }

        if (permissoes.contains("ROLE_ADMIN")) {
            return url + "/admin/inicial.jsf";
        }
        if (permissoes.contains("ROLE_USER")) {
            return url + "/usuario/inicial.jsf";
        }
        return url + "index.jsf";
    }
}
