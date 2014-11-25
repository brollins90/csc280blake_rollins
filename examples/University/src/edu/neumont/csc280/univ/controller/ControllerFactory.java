package edu.neumont.csc280.univ.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import edu.neumont.csc280.framework.AbstractControllerFactory;
import edu.neumont.csc280.framework.Controller;

/**
 * Conrete implementation of AbstractControllerFactory.  Simply attaches to the
 * EJB Container and specifies app-specific controllers.  (This way, the framework
 * package can be included in other projects.)
 * 
 * @author jcummings
 *
 */
@Stateless
@LocalBean
public class ControllerFactory extends AbstractControllerFactory {
	@Inject StudentController studentController;
	@Inject AccountController auctionController;
	
	@Produces
	public Map<OperationResourceInfo, ClassResourceInfo> getUrlMapping() {
		return super.getUrlMapping();
	}
	
	@Override
	protected List<? extends Controller> getControllers() {
		return Arrays.asList(auctionController, studentController);
	}

}
