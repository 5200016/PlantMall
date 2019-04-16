import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysCollection } from 'app/shared/model/sys-collection.model';
import { SysCollectionService } from './sys-collection.service';

@Component({
    selector: 'jhi-sys-collection-delete-dialog',
    templateUrl: './sys-collection-delete-dialog.component.html'
})
export class SysCollectionDeleteDialogComponent {
    sysCollection: ISysCollection;

    constructor(
        protected sysCollectionService: SysCollectionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysCollectionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysCollectionListModification',
                content: 'Deleted an sysCollection'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-collection-delete-popup',
    template: ''
})
export class SysCollectionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCollection }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysCollectionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysCollection = sysCollection;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
