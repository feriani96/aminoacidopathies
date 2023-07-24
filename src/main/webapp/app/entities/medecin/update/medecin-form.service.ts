import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMedecin, NewMedecin } from '../medecin.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMedecin for edit and NewMedecinFormGroupInput for create.
 */
type MedecinFormGroupInput = IMedecin | PartialWithRequiredKeyOf<NewMedecin>;

type MedecinFormDefaults = Pick<NewMedecin, 'id'>;

type MedecinFormGroupContent = {
  id: FormControl<IMedecin['id'] | NewMedecin['id']>;
  nom: FormControl<IMedecin['nom']>;
  prenom: FormControl<IMedecin['prenom']>;
  cin: FormControl<IMedecin['cin']>;
  email: FormControl<IMedecin['email']>;
  tel: FormControl<IMedecin['tel']>;
  adresse: FormControl<IMedecin['adresse']>;
  etablissement: FormControl<IMedecin['etablissement']>;
  servicesante: FormControl<IMedecin['servicesante']>;
};

export type MedecinFormGroup = FormGroup<MedecinFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MedecinFormService {
  createMedecinFormGroup(medecin: MedecinFormGroupInput = { id: null }): MedecinFormGroup {
    const medecinRawValue = {
      ...this.getFormDefaults(),
      ...medecin,
    };
    return new FormGroup<MedecinFormGroupContent>({
      id: new FormControl(
        { value: medecinRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      nom: new FormControl(medecinRawValue.nom, {
        validators: [Validators.required],
      }),
      prenom: new FormControl(medecinRawValue.prenom, {
        validators: [Validators.required],
      }),
      cin: new FormControl(medecinRawValue.cin),
      email: new FormControl(medecinRawValue.email),
      tel: new FormControl(medecinRawValue.tel),
      adresse: new FormControl(medecinRawValue.adresse),
      etablissement: new FormControl(medecinRawValue.etablissement),
      servicesante: new FormControl(medecinRawValue.servicesante),
    });
  }

  getMedecin(form: MedecinFormGroup): IMedecin | NewMedecin {
    return form.getRawValue() as IMedecin | NewMedecin;
  }

  resetForm(form: MedecinFormGroup, medecin: MedecinFormGroupInput): void {
    const medecinRawValue = { ...this.getFormDefaults(), ...medecin };
    form.reset(
      {
        ...medecinRawValue,
        id: { value: medecinRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MedecinFormDefaults {
    return {
      id: null,
    };
  }
}
