class Api::V1::EventsController < Api::V1::ApplicationController
  before_action :set_event, only: [:show]

  # GET /events
  # GET /events.json
  def index
    @events = Event.all
    # byebug
    page = params[:page] || 1
    per = params[:per] || 100

    respond_with @events.page(page).per(per)
  end

  # GET /events/1
  # GET /events/1.json
  def show
    respond_with @event
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_event
      @event = Event.find(params[:id])
    end
end
