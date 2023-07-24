import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStructurefiche, NewStructurefiche } from '../structurefiche.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStructurefiche for edit and NewStructureficheFormGroupInput for create.
 */
type StructureficheFormGroupInput = IStructurefiche | PartialWithRequiredKeyOf<NewStructurefiche>;

type StructureficheFormDefaults = Pick<NewStructurefiche, 'id'>;

type StructureficheFormGroupContent = {
  id: FormControl<IStructurefiche['id'] | NewStructurefiche['id']>;
  typestructure: FormControl<IStructurefiche['typestructure']>;
  ordre: FormControl<IStructurefiche['ordre']>;
  etablissement: FormControl<IStructurefiche['etablissement']>;
  servicesante: FormControl<IStructurefiche['servicesante']>;
  medecin: FormControl<IStructurefiche['medecin']>;
};

export type StructureficheFormGroup = FormGroup<StructureficheFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StructureficheFormService {
  createStructureficheFormGroup(structurefiche: StructureficheFormGroupInput = { id: null }): StructureficheFormGroup {
    const structureficheRawValue = {
      ...this.getFormDefaults(),
      ...structurefiche,
    };
    return new FormGroup<StructureficheFormGroupContent>({
      id: new FormControl(
        { value: structureficheRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      typestructure: new FormControl(structureficheRawValue.typestructure, {
        validators: [Validators.required],
      }),
      ordre: new FormControl(structureficheRawValue.ordre, {
        validators: [Validators.required],
      }),
      etablissement: new FormControl(structureficheRawValue.etablissement, {
        validators: [Validators.required],
      }),
      servicesante: new FormControl(structureficheRawValue.servicesante),
      medecin: new FormControl(structureficheRawValue.medecin),
    });
  }

  getStructurefiche(form: StructureficheFormGroup): IStructurefiche | NewStructurefiche {
    return form.getRawValue() as IStructurefiche | NewStructurefiche;
  }

  resetForm(form: StructureficheFormGroup, structurefiche: StructureficheFormGroupInput): void {
    const structureficheRawValue = { ...this.getFormDefaults(), ...structurefiche };
    form.reset(
      {
        ...structureficheRawValue,
        id: { value: structureficheRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): StructureficheFormDefaults {
    return {
      id: null,
    };
  }
}
