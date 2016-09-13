package sample.sources;

/**
 * Created by alexander on 13.09.16.
 */

public class KohonenNetwork {

    private  Input[] _inputs;
    private  Neuron[] _neurons;

    public KohonenNetwork() {
        _inputs = new Input[1600];

        Neuron a = new Neuron("A");
        Neuron b = new Neuron("B");
        Neuron c = new Neuron("C");
        Neuron d = new Neuron("D");

        for (int i = 0; i < 1600; i++) {
            Link toA = new Link(a);
            Link toB = new Link(b);
            Link toC = new Link(c);
            Link toD = new Link(d);

            Link[] links = new Link[]{ toA, toB, toC, toD };
            _inputs[i] = new Input(links);

            a.addLink(toA);
            b.addLink(toB);
            c.addLink(toC);
            d.addLink(toD);
        }

        _neurons = new Neuron[] { a, b, c, d };
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
        for (int i = 0; i < neuron.incomingLinks.size(); i++)
        {
            Link incomingLink = neuron.get(i);
            incomingLink.weight = incomingLink.weight + 0.5 * (input[i] - incomingLink.weight);
        }
    }

    public Neuron getLucky(int position) {
        return _neurons[position];
    }
}
