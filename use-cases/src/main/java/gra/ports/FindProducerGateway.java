package gra.ports;

import gra.Producer;

public interface FindProducerGateway {

	Producer execute(Long id);

}
