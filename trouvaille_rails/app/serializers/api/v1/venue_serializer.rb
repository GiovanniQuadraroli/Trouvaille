class Api::V1::VenueSerializer < ActiveModel::Serializer
  attributes :id, :name, :address_1, :city, :state, :country, :location
end
