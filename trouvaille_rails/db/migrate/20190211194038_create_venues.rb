class CreateVenues < ActiveRecord::Migration[5.2]
  def change
    create_table :venues, id: :uuid do |t|
      t.string :name
      t.string :address_1
      t.string :address_1
      t.string :city
      t.string :state
      t.string :country
      t.string :postal_code
      t.string :location
      t.timestamps
    end
  end
end
