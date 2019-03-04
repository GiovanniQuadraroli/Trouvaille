50.times do
    Event.seed(:id) do |event|
        event.id = SecureRandom.uuid,
        event.title = "#{Faker::Artist.name} Live",
        event.description = Faker::Lorem.sentence,
        event.start_time = Faker::Date.forward
    end
end
