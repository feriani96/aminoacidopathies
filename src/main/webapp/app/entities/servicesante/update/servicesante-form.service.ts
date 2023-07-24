import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IServicesante, NewServicesante } from '../servicesante.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IServicesante for edit and NewServicesanteFormGroupInput for create.
 */
type ServicesanteFormGroupInput = IServicesante | PartialWithRequiredKeyOf<NewServicesante>;

type ServicesanteFormDefaults = Pick<NewServicesante, 'id'>;

type ServicesanteFormGroupContent = {
  id: FormControl<IServicesante['id'] | NewServicesante['id']>;
  nom: FormControl<IServicesante['nom']>;
};

export type ServicesanteFormGroup = FormGroup<ServicesanteFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ServicesanteFormService {
  createServicesanteFormGroup(servicesante: ServicesanteFormGroupInput = { id: null }): ServicesanteFormGroup {
    const servicesanteRawValue = {
      ...this.getFormDefaults(),
      ...servicesante,
    };
    return new FormGroup<ServicesanteFormGroupContent>({
      id: new FormControl(
        { value: servicesanteRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      nom: new FormControl(servicesanteRawValue.nom, {
        validators: [Validators.required],
      }),
    });
  }

  getServicesante(form: ServicesanteFormGroup): IServicesante | NewServicesante {
    return form.getRawValue() as IServicesante | NewServicesante;
  }

  resetForm(form: ServicesanteFormGroup, servicesante: ServicesanteFormGroupInput): void {
    const servicesanteRawValue = { ...this.getFormDefaults(), ...servicesante };
    form.reset(
      {
        ...servicesanteRawValue,
        id: { value: servicesanteRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ServicesanteFormDefaults {
    return {
      id: null,
    };
  }
}
