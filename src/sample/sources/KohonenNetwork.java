package sample.sources;

/**
 * Created by alexander on 13.09.16.
 */

public class KohonenNetwork {

    private  Input[] _inputs;
    private  Neuron[] _neurons;

    public int Handle(int[] input)
    {
        for (int i = 0; i < _inputs.length; i++)
        {
            Input inputNeuron = _inputs[i];
            for (Link outgoingLink: inputNeuron.outgoingLikns)
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
}
