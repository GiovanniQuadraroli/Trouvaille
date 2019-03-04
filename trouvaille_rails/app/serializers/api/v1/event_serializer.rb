class Api::V1::EventSerializer < ActiveModel::Serializer
  attributes :id, :title, :description, :start_time, :end_time

  has_one :venue
end
