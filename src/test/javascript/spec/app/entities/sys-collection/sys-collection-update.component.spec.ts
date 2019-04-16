/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCollectionUpdateComponent } from 'app/entities/sys-collection/sys-collection-update.component';
import { SysCollectionService } from 'app/entities/sys-collection/sys-collection.service';
import { SysCollection } from 'app/shared/model/sys-collection.model';

describe('Component Tests', () => {
    describe('SysCollection Management Update Component', () => {
        let comp: SysCollectionUpdateComponent;
        let fixture: ComponentFixture<SysCollectionUpdateComponent>;
        let service: SysCollectionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCollectionUpdateComponent]
            })
                .overrideTemplate(SysCollectionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCollectionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCollectionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCollection(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCollection = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCollection();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCollection = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
