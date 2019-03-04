ActiveAdmin.register Venue do
    permit_params :name, :address_1, :city, :state, :country, :postal_code

    index do
      selectable_column
    #   id_column
      column :name
      column :address_1
      column :city
      column :state
      column :country
      column :postal_code
      actions
    end

    filter :name
    filter :city

    form do |f|
      f.inputs do
        f.input :name
        f.input :address_1
        f.input :city
        f.input :state
        f.input :country
        f.input :postal_code
      end
      f.actions
    end
  end
