import java.util.Scanner;
import java.util.Stack;

class Pino {
    private int numero; // número identificador do pino
    private Stack<Integer> discos; // pilha de discos

    public Pino(int numero) {
        this.numero = numero;
        this.discos = new Stack<Integer>(); // inicializa a pilha vazia
    }

    public int getNumero() {
        return this.numero;
    }

    public void empilhar(int disco) {
        this.discos.push(disco); // adiciona um disco ao topo da pilha
    }

    public int desempilhar() {
        return this.discos.pop(); // remove e retorna o disco do topo da pilha
    }

    public boolean estaVazio() {
        return this.discos.empty(); // verifica se a pilha está vazia
    }

    public int topo() {
        return this.discos.peek(); // retorna o disco do topo da pilha
    }
}

public class Torre {
    private static int movimentos = 0;

    private static void mover(Pino pinoOrigem, Pino pinoDestino) {

        System.out.format("Mover disco %d do pino %d para o pino %d\n", pinoOrigem.topo(), pinoOrigem.getNumero(),
                pinoDestino.getNumero());
        int disco = pinoOrigem.desempilhar(); // remove o disco do topo do pino de origem
        pinoDestino.empilhar(disco); // adiciona o disco ao topo do pino de destino
        movimentos++;
    }

    public static void hanoi(int n, Pino pinoOrigem, Pino pinoDestino, Pino pinoTrabalho) {
        if (n > 0) { // condição de parada da recursão
            hanoi(n - 1, pinoOrigem, pinoTrabalho, pinoDestino); // move os n-1 discos do pino de origem para o pino
                                                                 // intermediário
            mover(pinoOrigem, pinoDestino); // move o último disco do pino de origem para o pino de destino
            hanoi(n - 1, pinoTrabalho, pinoDestino, pinoOrigem); // move os n-1 discos do pino intermediário para o pino
                                                                 // de destino
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número de discos: ");
        int n = scanner.nextInt();

        if (n <= 0) { // verifica se o número de discos é válido
            throw new IllegalArgumentException("O número de discos deve ser maior que zero.");
        }

        Pino pinoOrigem = new Pino(1); // cria o pino de origem com número 1
        Pino pinoDestino = new Pino(3); // cria o pino de destino com número 3
        Pino pinoTrabalho = new Pino(2); // cria o pino intermediário com número 2

        // Empilhar os discos no pino de origem
        for (int i = n; i > 0; i--) {
            pinoOrigem.empilhar(i); // adiciona os discos ao
        }

        // Começar a medir o tempo
        long inicio = System.currentTimeMillis();

        // Executar o algoritmo
        hanoi(n, pinoOrigem, pinoDestino, pinoTrabalho);

        // Parar de medir o tempo e calcular o tempo gasto
        long fim = System.currentTimeMillis();
        long tempoGasto = fim - inicio;
        // Converter milissegundos em horas, minutos, segundos e milissegundos
        long milissegundos = fim - inicio;
        long horas = milissegundos / 3600000;
        long minutos = (milissegundos % 3600000) / 60000;
        long segundos = (milissegundos % 60000) / 1000;
        long milis = milissegundos % 1000;

        // Exibir o tempo gasto formatado
        System.out.format("Tempo gasto: %02d:%02d:%02d:%03d\n", horas, minutos, segundos, milis);
        // Exibir o tempo gasto
        System.out.println("Tempo gasto: " + tempoGasto + " ms");
        System.out.println("numeros de movimentos foi de: " + movimentos);
    }
}