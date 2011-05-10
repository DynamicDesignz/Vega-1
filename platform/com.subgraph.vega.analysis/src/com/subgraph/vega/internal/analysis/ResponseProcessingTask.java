package com.subgraph.vega.internal.analysis;

import java.util.List;

import org.apache.http.HttpRequest;

import com.subgraph.vega.api.http.requests.IHttpResponse;
import com.subgraph.vega.api.model.IWorkspace;
import com.subgraph.vega.api.model.alerts.IScanInstance;
import com.subgraph.vega.api.scanner.modules.IResponseProcessingModule;

public class ResponseProcessingTask implements Runnable {
	
	private final IScanInstance scanInstance;
	private final HttpRequest request;
	private final IHttpResponse response;
	private final IWorkspace workspace;
	private final List<IResponseProcessingModule> modules;
		
	ResponseProcessingTask(IScanInstance scanInstance, HttpRequest request, IHttpResponse response, IWorkspace workspace, List<IResponseProcessingModule> modules) {
		this.scanInstance = scanInstance;
		this.request = request;
		this.response = response;
		this.workspace = workspace;
		this.modules = modules;
	}

	@Override
	public void run() {
		for(IResponseProcessingModule m: modules)
			m.processResponse(scanInstance, request, response, workspace);		
	}
}
