package com.guilda.aventureiros.calculadora;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calcular")
public class CalculadoraController {

    @RequestMapping(value = "/soma", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Double> soma(@RequestParam Double a, @RequestParam Double b) {
        return ResponseEntity.ok(a + b);
    }

    @RequestMapping(value = "/subtracao", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Double> subtracao(@RequestParam Double a, @RequestParam Double b) {
        return ResponseEntity.ok(a - b);
    }

    @RequestMapping(value = "/multiplicacao", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Double> multiplicacao(@RequestParam Double a, @RequestParam Double b) {
        return ResponseEntity.ok(a * b);
    }

    @RequestMapping(value = "/divisao", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Double> divisao(@RequestParam Double a, @RequestParam Double b) {
        if (b == 0) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(a / b);
    }

    @RequestMapping(value = "/exponenciacao", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Double> exponenciacao(@RequestParam Double a, @RequestParam Double b) {
        return ResponseEntity.ok(Math.pow(a, b));
    }
}