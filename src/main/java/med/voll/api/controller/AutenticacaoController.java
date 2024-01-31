package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    public TokenService service;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
//        classe instanciado para ser utilizada como objeto dos dados de autenticação(geração do token)
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var token =  service.gerarToken((Usuario) authentication.getPrincipal());
//        devolvendo o token na request no record DadosTokenJWT
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

}
