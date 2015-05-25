package edu.nesterenko.airline.command;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.Response;

public interface Command {
  public Response processRequest(Request request);
}
