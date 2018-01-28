package com.mpsdevelopment.biopotential.server.eventbus;

import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.config.IBusConfiguration;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;
import net.engio.mbassy.subscription.Subscription;
import org.apache.log4j.Logger;

import java.util.Collection;

public class EventBus {

	private static final Logger LOGGER = Logger.getLogger(EventBus.class);

	private static HeliMbassador eventBus = null;

	private EventBus() {

	}

	static {
		getEventBus();
	}

	private static HeliMbassador getEventBus() {
		if (eventBus == null) {
			IBusConfiguration busConfiguration = new BusConfiguration().addFeature(Feature.SyncPubSub.Default()).addFeature(Feature.AsynchronousHandlerInvocation.Default())
					.addFeature(Feature.AsynchronousMessageDispatch.Default()).addPublicationErrorHandler(new IPublicationErrorHandler() {
						@Override
						public void handleError(PublicationError error) {
							LOGGER.warn("Handled error while publishing event.");
						}
					});
			eventBus = new HeliMbassador(busConfiguration);
		}
		return eventBus;
	}

	public static void publishEvent(Event event) {
		try {
			getEventBus().publish(event);

		} catch (Throwable e) {
		}
	}

	public static void subscribe(Object subscriber) {
		getEventBus().subscribe(subscriber);
	}

	public static boolean unsubscribe(Object subscriber) {
		return getEventBus().unsubscribe(subscriber);
	}

	public static Collection<Subscription> getSubscribers(Class messageType) {
		return getEventBus().getSubscriptions(messageType);
	}
}
