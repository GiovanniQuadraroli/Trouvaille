class Event < ApplicationRecord
    acts_as_taggable

    belongs_to :venue
end
