import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { PathologieFormService, PathologieFormGroup } from './pathologie-form.service';
import { IPathologie } from '../pathologie.model';
import { PathologieService } from '../service/pathologie.service';

@Component({
  selector: 'jhi-pathologie-update',
  templateUrl: './pathologie-update.component.html',
})
export class PathologieUpdateComponent implements OnInit {
  isSaving = false;
  pathologie: IPathologie | null = null;

  editForm: PathologieFormGroup = this.pathologieFormService.createPathologieFormGroup();

  constructor(
    protected pathologieService: PathologieService,
    protected pathologieFormService: PathologieFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pathologie }) => {
      this.pathologie = pathologie;
      if (pathologie) {
        this.updateForm(pathologie);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pathologie = this.pathologieFormService.getPathologie(this.editForm);
    if (pathologie.id !== null) {
      this.subscribeToSaveResponse(this.pathologieService.update(pathologie));
    } else {
      this.subscribeToSaveResponse(this.pathologieService.create(pathologie));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPathologie>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(pathologie: IPathologie): void {
    this.pathologie = pathologie;
    this.pathologieFormService.resetForm(this.editForm, pathologie);
  }
}
