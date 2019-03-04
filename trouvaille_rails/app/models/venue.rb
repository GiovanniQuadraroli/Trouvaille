class Venue < ApplicationRecord
    acts_as_taggable

    has_many :events
    accepts_nested_attributes_for :events
end
