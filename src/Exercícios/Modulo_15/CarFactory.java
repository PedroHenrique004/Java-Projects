package Exercícios.Modulo_15;

// Interface abstrata para a fábrica de carros
abstract class CarFactory {
    public abstract ElectricCar createElectricCar();
    public abstract CombustionCar createCombustionCar();
}

// Classe concreta para a fábrica de carros elétricos
class ElectricCarFactory extends CarFactory {
    @Override
    public ElectricCar createElectricCar() {
        return new ElectricCar("Tesla Model 3", 300); // Exemplo fictício
    }

    @Override
    public CombustionCar createCombustionCar() {
        return null;
    }
}

class CombustionCarFactory extends CarFactory {
    @Override
    public ElectricCar createElectricCar() {
        return null;
    }

    @Override
    public CombustionCar createCombustionCar() {
        return new CombustionCar("Ford Mustang", 5000); // Exemplo fictício
    }
}

class ElectricCar {
    String model;
    int range;

    ElectricCar(String model, int range) {
        this.model = model;
        this.range = range;
    }
}

class CombustionCar {
    String model;
    int displacement;

    CombustionCar(String model, int displacement) {
        this.model = model;
        this.displacement = displacement;
    }
}
