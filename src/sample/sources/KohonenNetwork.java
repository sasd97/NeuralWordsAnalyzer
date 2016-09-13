package sample.sources;

/**
 * Created by alexander on 13.09.16.
 */

public class KohonenNetwork {

    private  Input[] _inputs;
    private  Neuron[] _neurons;

    public KohonenNetwork() {
        Neuron a = new Neuron("A");
        Neuron b = new Neuron("B");
        Neuron c = new Neuron("C");
        Neuron d = new Neuron("D");

        _neurons = new Neuron[4];
        _inputs = new Input[1600];

        for (Input i:_inputs) i = new Input();
    }

    public int Handle(int[] input)
    {
        for (int i = 0; i < _inputs.length; i++)
        {
            Input inputNeuron = _inputs[i];
            for (Link outgoingLink: inputNeuron.outgoingLinks)
            {
                outgoingLink.neuron.power += outgoingLink.weight * input[i];
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < _neurons.length; i++)
        {
            if (_neurons[i].power > _neurons[maxIndex].power)
                maxIndex = i;
        }

        for (Neuron neuron: _neurons)
        {
            neuron.power = 0;
        }
        return maxIndex;
    }

    public void Study(int[] input, int correctAnswer)
    {
        Neuron neuron = _neurons[correctAnswer];
        for (int i = 0; i < neuron.incomingLinks.length; i++)
        {
            Link incomingLink = neuron.incomingLinks[i];
            incomingLink.weight = incomingLink.weight + 0.5 * (input[i] - incomingLink.weight);
        }
    }
}
