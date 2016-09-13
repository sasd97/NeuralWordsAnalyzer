package sample.sources;

import sample.sources.models.InputModel;
import sample.sources.models.LinkModel;
import sample.sources.models.NeuronModel;

/**
 * Created by alexander on 13.09.16.
 */

public class Network {

    private  InputModel[] _inputs;
    private  NeuronModel[] _neurons;

    public Network() {
        _inputs = new InputModel[1600];

        NeuronModel a = new NeuronModel("A");
        NeuronModel b = new NeuronModel("B");
        NeuronModel c = new NeuronModel("C");
        NeuronModel d = new NeuronModel("D");

        for (int i = 0; i < 1600; i++) {
            LinkModel toA = new LinkModel(a);
            LinkModel toB = new LinkModel(b);
            LinkModel toC = new LinkModel(c);
            LinkModel toD = new LinkModel(d);

            LinkModel[] links = new LinkModel[]{ toA, toB, toC, toD };
            _inputs[i] = new InputModel(links);

            a.addLink(toA);
            b.addLink(toB);
            c.addLink(toC);
            d.addLink(toD);
        }

        _neurons = new NeuronModel[] { a, b, c, d };
    }

    public int Handle(int[] input)
    {
        for (int i = 0; i < _inputs.length; i++)
        {
            InputModel inputNeuron = _inputs[i];
            for (LinkModel outgoingLink: inputNeuron.outgoingLinks)
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

        for (NeuronModel neuron: _neurons)
        {
            neuron.power = 0;
        }
        return maxIndex;
    }

    public void Study(int[] input, int correctAnswer)
    {
        NeuronModel neuron = _neurons[correctAnswer];
        for (int i = 0; i < neuron.incomingLinks.size(); i++)
        {
            LinkModel incomingLink = neuron.get(i);
            incomingLink.weight = incomingLink.weight + 0.5 * (input[i] - incomingLink.weight);
        }
    }

    public NeuronModel getLucky(int position) {
        return _neurons[position];
    }
}
