package Exercícios.Modulo_15;

public class Main {
    public static void main(String[] args) {
        // Criando uma fábrica de carros elétricos
        CarFactory electricCarFactory = new ElectricCarFactory();
        ElectricCar electricCar = electricCarFactory.createElectricCar();

        // Criando uma fábrica de carros a combustão
        CarFactory combustionCarFactory = new CombustionCarFactory();
        CombustionCar combustionCar = combustionCarFactory.createCombustionCar();

        // Exibindo informações sobre os carros
        System.out.println("Carro elétrico:");
        System.out.println("Modelo: " + electricCar.model);
        System.out.println("Autonomia: " + electricCar.range + " km");

        System.out.println("\nCarro a combustão:");
        System.out.println("Modelo: " + combustionCar.model);
        System.out.println("Cilindrada: " + combustionCar.displacement + " cc");
    }
}
