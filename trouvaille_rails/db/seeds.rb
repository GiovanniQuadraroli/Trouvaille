# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)
unless Admin.find_by_email('admin@example.com')
    Admin.create!(email: 'admin@example.com', password: 'password', password_confirmation: 'password')
end

seed_tags = ["restaurant", "bar", "pub", "live music", "club", "board games", "cosy bar",
    "museum", "exhibition", "jazz", "pop", "rock", "electronic music", "indoor", "outdoor",
    "green space", "quiet", "aperitivo", "fine dining","take-away", "casual drinking", "cafe",
    "italian cuisine", "japanese cuisine", "indian cuisine", "chinese cuisine", "thai cuisine",
    "fast food"]

unless Tag.all.count > 0
    seed_tags.each do |tag_name|
        Tag.create(name: tag_name)
    end
end

unless Venue.all.count > 0
    def random_point_location()
        lat = 41.8889957 + Random.rand * (41.9046353 - 41.8889957)
        lon = 12.4921491 + Random.rand * (12.5373027 - 12.4921491)

        return "POINT (#{lon} #{lat})"
    end

    50.times do
        Venue.create(
            name: Faker::Restaurant.name,
            address_1: Faker::Address.street_address,
            city: Faker::Address.city,
            state: Faker::Address.state,
            country: Faker::Address.country,
            postal_code: Faker::Address.postcode,
            location: random_point_location,
            tag_list: seed_tags.sample(2).join(", "),
            events_attributes: [
                {
                    title: "#{Faker::Artist.name} Live",
                    description: Faker::Lorem.sentence,
                    start_time: Faker::Date.forward,
                    tag_list: seed_tags.sample(3).join(", ")
                }
            ]
        )
    end
end
