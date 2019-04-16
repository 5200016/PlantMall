/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysCollectionDeleteDialogComponent } from 'app/entities/sys-collection/sys-collection-delete-dialog.component';
import { SysCollectionService } from 'app/entities/sys-collection/sys-collection.service';

describe('Component Tests', () => {
    describe('SysCollection Management Delete Component', () => {
        let comp: SysCollectionDeleteDialogComponent;
        let fixture: ComponentFixture<SysCollectionDeleteDialogComponent>;
        let service: SysCollectionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCollectionDeleteDialogComponent]
            })
                .overrideTemplate(SysCollectionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCollectionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCollectionService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
